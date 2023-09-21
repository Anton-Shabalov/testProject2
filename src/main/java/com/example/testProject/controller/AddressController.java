package com.example.testProject.controller;

import com.example.testProject.dto.AddressDTO;
import com.example.testProject.service.AddressService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/{idPerson}/addresses")
public class AddressController {
    private final AddressService addressService;

    @GetMapping()
    public List<AddressDTO> getPhoneNumbers(@PathVariable @Min(0) Long idPerson) {
        return addressService.getAddresses(idPerson);
    }

    @DeleteMapping("/{idAddress}")
    public void deleteAddress(@PathVariable @Min(0) Long idPerson,
                              @PathVariable @Min(0) Long idAddress) {
        addressService.deleteAddress(idPerson, idAddress);
    }

    @PostMapping
    public void addAddress(@PathVariable @Min(0) Long idPerson,
                           @Valid @RequestBody AddressDTO addressDTO) {
        addressService.addAddress(idPerson, addressDTO);
    }

    @PutMapping("/{idAddress}")
    public void updatePhoneNumbers(@PathVariable @Min(0) Long idPerson,
                                   @PathVariable @Min(0) Long idAddress,
                                   @Valid @RequestBody AddressDTO addressDTO) {
        addressService.updateAddress(idPerson, idAddress, addressDTO);

    }
}
