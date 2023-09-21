package com.example.testProject.repository;

import com.example.testProject.entity.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {
    List<Phone> findByPersonId(Long personId);

    boolean existsByNumber(String number);

    Optional<Phone> findByNumber(String number);


}
