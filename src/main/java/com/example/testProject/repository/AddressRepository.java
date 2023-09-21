package com.example.testProject.repository;

import com.example.testProject.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findByPersonId(Long addressId);

}
