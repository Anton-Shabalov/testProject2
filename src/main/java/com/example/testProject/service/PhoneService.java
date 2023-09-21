package com.example.testProject.service;

import com.example.testProject.dto.PhoneDTO;
import com.example.testProject.entity.Person;
import com.example.testProject.entity.Phone;
import com.example.testProject.exception.CustomEntityNotFoundException;
import com.example.testProject.exception.EntityNotBelongException;
import com.example.testProject.exception.PhoneAlreadyExistsException;
import com.example.testProject.repository.PersonRepository;
import com.example.testProject.repository.PhoneRepository;
import com.example.testProject.util.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PhoneService {
    private final ModelMapper modelMapper;
    private final PhoneRepository phoneRepository;
    private final PersonRepository personRepository;

    public void addPhoneNumbers(Long personId, PhoneDTO phoneDTO) {
        Optional<Person> optionalPerson = personRepository.findById(personId);
        Person person = optionalPerson.orElseThrow(() -> new CustomEntityNotFoundException(personId, Person.class.getName()));
        if (checkExistPhones(phoneDTO)) throw new PhoneAlreadyExistsException(phoneDTO.getNumber());
        Phone phone = modelMapper.convertPhoneDtoToPhone(phoneDTO);
        phone.setPerson(person);
        phoneRepository.save(phone);
    }

    public void deletePhoneNumbers(Long idPerson, Long idPhoneNumbers) {
        Phone phone = phoneRepository.findById(idPhoneNumbers)
                .orElseThrow(() -> new CustomEntityNotFoundException(idPhoneNumbers, Phone.class.getName()));
        if (phone.getPerson().getId().equals(idPerson)) {
            phoneRepository.deleteById(idPhoneNumbers);
        } else {
            throw new EntityNotBelongException(Person.class.getName(), Phone.class.getName());
        }
    }

    public List<PhoneDTO> getPhoneNumbers(Long personId) {
        if (!personRepository.existsById(personId))
            throw new CustomEntityNotFoundException(personId, Person.class.getName());
        return phoneRepository.findByPersonId(personId).stream().map(modelMapper::convertPhoneToPhoneDTO).toList();
    }

    public void updatePhone(Long idPerson, Long idPhoneNumbers, PhoneDTO phoneDTO) {
        Optional<Phone> optionalPhone = phoneRepository.findById(idPhoneNumbers);
        Phone phone = optionalPhone.orElseThrow(() -> new CustomEntityNotFoundException(idPhoneNumbers, Phone.class.getName()));
        Phone newPhone = modelMapper.convertPhoneDtoToPhone(phoneDTO);
        if (!phone.getNumber().equals(newPhone.getNumber()) && checkExistPhones(phoneDTO)) {
            throw new PhoneAlreadyExistsException(phoneDTO.getNumber());
        }
        newPhone.setId(phone.getId());
        newPhone.setPerson(phone.getPerson());
        if (phone.getPerson().getId().equals(idPerson)) {
            phoneRepository.save(newPhone);
        } else {
            throw new EntityNotBelongException(Person.class.getName(), Phone.class.getName());
        }

    }

    boolean checkExistPhones(PhoneDTO phoneDTO) {
        return phoneRepository.existsByNumber(phoneDTO.getNumber());
    }

    public Long getPersonIdByPhoneNumber(String phoneNumber) {
        return phoneRepository.findByNumber(phoneNumber).
                orElseThrow(() -> new EntityNotFoundException("Phone " + phoneNumber + " not found")).
                getPerson().getId();

    }

}
