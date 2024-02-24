package org.awlabs.dr.project.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awlabs.dr.project.model.User;
import org.awlabs.dr.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private static final Logger LOG = LogManager.getLogger("UserService");


    @Autowired
    private UserRepository userRepository;

    public Long registerNewUser(User user) {
        LOG.debug("Registering new user with details. email: {} firstName: {}, lastName: {}", user.getEmail(), user.getFirstName(), user.getLastName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}

