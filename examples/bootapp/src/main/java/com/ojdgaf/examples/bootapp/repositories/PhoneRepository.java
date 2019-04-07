package com.ojdgaf.examples.bootapp.repositories;

import com.ojdgaf.examples.bootapp.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    Phone findByNumber(String number);
}
