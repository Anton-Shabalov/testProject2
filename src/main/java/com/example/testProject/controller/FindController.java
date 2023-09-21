package com.example.testProject.controller;

import com.example.testProject.dto.PersonDTO;
import com.example.testProject.service.PersonService;
import com.example.testProject.service.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/find-person")
public class FindController {
    private final PhoneService phoneService;
    private final PersonService personService;

    @GetMapping("/name/{namePerson}")
    public List<PersonDTO> getPersonByName(@PathVariable String namePerson) {
        return personService.getPersonByName(namePerson);
    }

    @GetMapping("/surname/{surnamePerson}")
    public List<PersonDTO> getPersonBySurname(@PathVariable String surnamePerson) {
        return personService.getPersonBySurname(surnamePerson);
    }

    @GetMapping("/name/{namePerson}/surname/{surnamePerson}")
    public List<PersonDTO> getPersonByNameAndSurname(@PathVariable String namePerson,
                                                     @PathVariable String surnamePerson) {
        return personService.getPersonByNameAndSurname(namePerson, surnamePerson);
    }

    @GetMapping("/number/{phoneNumber}")
    public PersonDTO getPersonByPhoneNumber(@PathVariable String phoneNumber) {
        return personService.getPerson(phoneService.getPersonIdByPhoneNumber(phoneNumber));
    }
}
