package com.ojdgaf.examples.bootapp.repositories;

import java.util.List;
import java.util.Optional;

import com.ojdgaf.examples.bootapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Integer> userIds);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
