package edu.kpi.iasa.mmsa.SportBetApp.controller;

import edu.kpi.iasa.mmsa.SportBetApp.configuration.security.jwt.JwtProcessor;
import edu.kpi.iasa.mmsa.SportBetApp.dto.AccountDto;
import edu.kpi.iasa.mmsa.SportBetApp.dto.JwtRequestDto;
import edu.kpi.iasa.mmsa.SportBetApp.dto.JwtResponseDto;
import edu.kpi.iasa.mmsa.SportBetApp.dto.RegistrationDto;
import edu.kpi.iasa.mmsa.SportBetApp.model.Account;
import edu.kpi.iasa.mmsa.SportBetApp.service.RoleService;
import edu.kpi.iasa.mmsa.SportBetApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.Collections;

@Controller
public class AuthenticationController {

    private static final String DEFAULT_ROLE = "PLAYER";

    private final AuthenticationManager authenticationManager;
    private final JwtProcessor jwtProcessor;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtProcessor jwtProcessor,
                                    UserDetailsService userDetailsService,
                                    UserService userService,
                                    PasswordEncoder passwordEncoder,
                                    RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.jwtProcessor = jwtProcessor;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponseDto> signIn(@RequestBody JwtRequestDto jwtRequestDto) {
        String username = jwtRequestDto.getLogin();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, jwtRequestDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtProcessor.createJwt(username,
                (Collection<GrantedAuthority>) userDetails.getAuthorities());
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<AccountDto> signUp(@RequestBody RegistrationDto registrationDto) {
        Account account = createAccount(registrationDto);
        return ResponseEntity.ok(createAccountDto(userService.createAccount(account)));
    }

    private AccountDto createAccountDto(Account account) {
        return new AccountDto(account.getUsername());
    }

    private Account createAccount(RegistrationDto registrationDto) {
        Account account = Account.builder()
                .username(registrationDto.getUsername())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .e_mail(registrationDto.getEmail())
                .status_(registrationDto.getStatus())
                .build();
        account.setRoles(Collections.singleton(roleService.getRoleByName(DEFAULT_ROLE)));
        return account;
    }
}
