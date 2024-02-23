package net.codejava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);


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

