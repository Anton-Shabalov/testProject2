package com.example.testProject.util;

import com.example.testProject.dto.AddressDTO;
import com.example.testProject.dto.PersonDTO;
import com.example.testProject.dto.PhoneDTO;
import com.example.testProject.entity.Address;
import com.example.testProject.entity.Person;
import com.example.testProject.entity.Phone;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ModelMapper {
    private final org.modelmapper.ModelMapper modelMapper;

    public Person convertPersonDtoToPersonWithInnerObject(PersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
        person.setAddresses(personDTO.getAddresses().stream()
                .map(addressDTO -> modelMapper.map(addressDTO, Address.class))
                .peek(address -> address.setPerson(person))
                .toList());
        person.setPhones(personDTO.getPhones().stream()
                .map(phoneDTO -> modelMapper.map(phoneDTO, Phone.class))
                .peek(phone -> phone.setPerson(person))
                .toList());
        return person;
    }

    public PersonDTO convertPersonToPersonDtoWithInnerObject(Person person) {
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        personDTO.setAddresses(person.getAddresses().stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList());
        personDTO.setPhones(person.getPhones().stream()
                .map(phone -> modelMapper.map(phone, PhoneDTO.class))
                .toList());
        return personDTO;

    }

    public PersonDTO convertPersonToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person convertPersonDtoToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    public Phone convertPhoneDtoToPhone(PhoneDTO phoneDTO) {
        return modelMapper.map(phoneDTO, Phone.class);
    }

    public PhoneDTO convertPhoneToPhoneDTO(Phone phone) {
        return modelMapper.map(phone, PhoneDTO.class);
    }

    public Address convertAddressDtoToAddress(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }

    public AddressDTO convertAddressToAdressDTO(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }

}
