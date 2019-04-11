package com.ojdgaf.examples.bootapp.controllers;

import java.util.Map;
import java.util.HashMap;

import com.ojdgaf.examples.bootapp.entities.User;
import com.ojdgaf.examples.bootapp.responses.JwtResponse;
import com.ojdgaf.examples.bootapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("login")
    public JwtResponse login(
            @RequestParam("username") String usernameOrEmail,
            @RequestParam String password
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usernameOrEmail, password)
        );

        User user = service.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        return new JwtResponse(service.createJwtToken(user));
    }

    @PostMapping("register")
    public JwtResponse register(@ModelAttribute User user) {
        service.register(user);

        return new JwtResponse(service.createJwtToken(user));
    }

    @DeleteMapping("users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @GetMapping("refresh")
    public JwtResponse refresh(Authentication auth) {
        String username = (String) auth.getPrincipal();

        String[] authorityNames = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("authorities", authorityNames);

        return new JwtResponse(service.createJwtToken(claims));
    }
}
