package com.example.testProject.service;

import com.example.testProject.dto.AddressDTO;
import com.example.testProject.entity.Address;
import com.example.testProject.entity.Person;
import com.example.testProject.exception.CustomEntityNotFoundException;
import com.example.testProject.exception.EntityNotBelongException;
import com.example.testProject.repository.AddressRepository;
import com.example.testProject.repository.PersonRepository;
import com.example.testProject.util.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {
    private final ModelMapper modelMapper;
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public List<AddressDTO> getAddresses(Long idPerson) {
        return addressRepository.findByPersonId(idPerson).stream().map(modelMapper::convertAddressToAdressDTO).toList();
    }

    public void deleteAddress(Long idPerson, Long idAddress) {
        Address address = addressRepository.findById(idAddress)
                .orElseThrow(() -> new CustomEntityNotFoundException(idAddress, Address.class.getName()));
        if (address.getPerson().getId().equals(idPerson)) {
            addressRepository.deleteById(idAddress);
        } else {
            throw new EntityNotBelongException(Person.class.getName(), Address.class.getName());
        }
    }

    public void addAddress(Long idPerson, AddressDTO addressDTO) {
        Optional<Person> optionalPerson = personRepository.findById(idPerson);
        Person person = optionalPerson.orElseThrow(() -> new CustomEntityNotFoundException(idPerson, Person.class.getName()));
        Address address = modelMapper.convertAddressDtoToAddress(addressDTO);
        address.setPerson(person);
        addressRepository.save(address);
    }

    public void updateAddress(Long idPerson, Long idAddress, AddressDTO addressDTO) {
        Optional<Address> optionalAddress = addressRepository.findById(idAddress);
        Address address = optionalAddress.orElseThrow(() -> new CustomEntityNotFoundException(idAddress, Address.class.getName()));
        Address newAddress = modelMapper.convertAddressDtoToAddress(addressDTO);
        newAddress.setId(address.getId());
        newAddress.setPerson(address.getPerson());
        if (address.getPerson().getId().equals(idPerson)) {
            addressRepository.save(newAddress);
        } else {
            throw new EntityNotBelongException(Person.class.getName(), Address.class.getName());
        }

    }
}
