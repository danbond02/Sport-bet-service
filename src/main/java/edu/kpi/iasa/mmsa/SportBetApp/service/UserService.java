package edu.kpi.iasa.mmsa.SportBetApp.service;

import edu.kpi.iasa.mmsa.SportBetApp.model.Account;
import edu.kpi.iasa.mmsa.SportBetApp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Account createAccount(Account account) {
        return userRepository.save(account);
    }
}
