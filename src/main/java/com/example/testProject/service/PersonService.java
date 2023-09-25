package com.example.testProject.service;

import com.example.testProject.dto.PersonDTO;
import com.example.testProject.dto.PhoneDTO;
import com.example.testProject.entity.Person;
import com.example.testProject.exception.CustomEntityNotFoundException;
import com.example.testProject.exception.PhoneAlreadyExistsException;
import com.example.testProject.repository.PersonRepository;
import com.example.testProject.util.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final PhoneService phoneService;

    public PersonDTO getPerson(Long personId) {
        Optional<Person> optionalPerson = personRepository.findById(personId);
        return optionalPerson
                .map(modelMapper::convertPersonToPersonDtoWithInnerObject)
                .orElseThrow(() -> new CustomEntityNotFoundException(personId, Person.class.getName()));
    }

    public List<PersonDTO> getAllPerson() {
        return personRepository.findAll().stream().map(modelMapper::convertPersonToPersonDTO).toList();
    }

    public void deletePerson(Long personId) {
        personRepository.deleteById(personId);
    }

    public void addPerson(PersonDTO personDTO) {
        List<String> busyPhone = personDTO.getPhones().stream().filter(phoneService::checkExistPhones).map(PhoneDTO::getNumber).toList();
        if (busyPhone.isEmpty()) {
            personRepository.save(modelMapper.convertPersonDtoToPersonWithInnerObject(personDTO));
        } else {
            throw new PhoneAlreadyExistsException(busyPhone.toString());
        }

    }

    public void updatePerson(Long personId, PersonDTO personDTO) {
        if (personRepository.existsById(personId)) {
            personDTO.setId(personId);
            personRepository.save(modelMapper.convertPersonDtoToPerson(personDTO));
        } else {
            throw new CustomEntityNotFoundException(personId, Person.class.getName());
        }

    }


    public List<PersonDTO> getPersonByName(String namePerson) {
        return personRepository.findByName(namePerson).stream().map(modelMapper::convertPersonToPersonDtoWithInnerObject).toList();
    }

    public List<PersonDTO> getPersonBySurname(String surname) {
        return personRepository.findBySurname(surname).stream().map(modelMapper::convertPersonToPersonDtoWithInnerObject).toList();
    }

    public List<PersonDTO> getPersonByNameAndSurname(String name, String surname) {
        return personRepository.findByNameAndSurname(name, surname).stream().map(modelMapper::convertPersonToPersonDtoWithInnerObject).toList();
    }
}
