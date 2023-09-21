package com.example.testProject.repository;

import com.example.testProject.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findAll();

    List<Person> findByName(String name);

    List<Person> findBySurname(String surname);

    List<Person> findByNameAndSurname(String name, String surname);


}
