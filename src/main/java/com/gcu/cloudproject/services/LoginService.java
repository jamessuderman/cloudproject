/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 02/08/2021
 */

package com.gcu.cloudproject.services;

import com.gcu.cloudproject.models.User;
import com.gcu.cloudproject.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return password.equals(user.getPassword());
    }
}