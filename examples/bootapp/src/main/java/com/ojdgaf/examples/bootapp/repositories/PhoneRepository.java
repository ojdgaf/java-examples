package com.ojdgaf.examples.bootapp.repositories;

import java.util.Optional;

import com.ojdgaf.examples.bootapp.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    Optional<Phone> findByNumber(String number);
}
