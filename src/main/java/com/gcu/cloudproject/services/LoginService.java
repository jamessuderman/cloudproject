/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 02/08/2021
 */

package com.gcu.cloudproject.services;

import com.gcu.cloudproject.models.User;
import com.gcu.cloudproject.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {
    Logger loginServiceLogger = LoggerFactory.getLogger(LoginService.class);

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validate(String username, String password) {
        loginServiceLogger.info("LoginService --- validate --- " + new Date().toString());
        User user = userRepository.findByUsername(username);
        return password.equals(user.getPassword());
    }
}