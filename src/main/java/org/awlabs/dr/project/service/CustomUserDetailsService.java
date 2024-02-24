package org.awlabs.dr.project.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awlabs.dr.project.model.CustomUserDetails;
import org.awlabs.dr.project.model.User;
import org.awlabs.dr.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {


    private static final Logger LOG = LogManager.getLogger("CustomUserDetailsService");


    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            LOG.info("User with email: {} not found.", username);
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

}
