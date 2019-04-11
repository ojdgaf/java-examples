package com.ojdgaf.examples.bootapp.services;

import java.util.Map;
import java.util.HashMap;
import javax.persistence.EntityNotFoundException;

import com.ojdgaf.examples.bootapp.config.jwt.JwtProvider;
import com.ojdgaf.examples.bootapp.entities.User;
import com.ojdgaf.examples.bootapp.exceptions.RegistrationException;
import com.ojdgaf.examples.bootapp.repositories.RoleRepository;
import com.ojdgaf.examples.bootapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public User findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public void register(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new RegistrationException("Username is already in use");

        if (userRepository.existsByEmail(user.getEmail()))
            throw new RegistrationException("Email is already in use");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(roleRepository.findByName("ROLE_USER").get());

        userRepository.save(user);
    }

    public String createJwtToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getUsername());
        claims.put("authorities", user.getRoleNames());

        return createJwtToken(claims);
    }

    public String createJwtToken(Map<String, Object> claims) {
        return jwtProvider.createToken(claims);
    }
}
