package edu.kpi.iasa.mmsa.SportBetApp.service;

import edu.kpi.iasa.mmsa.SportBetApp.configuration.security.UserPrincipal;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.UserNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.model.Account;
import edu.kpi.iasa.mmsa.SportBetApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return new UserPrincipal(user);
    }
}
