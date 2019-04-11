package com.ojdgaf.examples.bootapp.repositories;

import java.util.Optional;

import com.ojdgaf.examples.bootapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
