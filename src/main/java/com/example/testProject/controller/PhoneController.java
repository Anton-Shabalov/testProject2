package com.example.testProject.controller;

import com.example.testProject.dto.PhoneDTO;
import com.example.testProject.service.PhoneService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/{idPerson}/phone-numbers")
public class PhoneController {
    private final PhoneService phoneService;

    @PutMapping("/{idPhoneNumbers}")
    public void updatePhoneNumbers(@PathVariable @Min(0) Long idPerson,
                                   @PathVariable @Min(0) Long idPhoneNumbers,
                                   @Valid @RequestBody PhoneDTO phoneDTO) {
        phoneService.updatePhone(idPerson, idPhoneNumbers, phoneDTO);

    }

    @DeleteMapping("/{idPhoneNumbers}")
    public void deletePhoneNumbers(@PathVariable @Min(0) Long idPerson,
                                   @PathVariable @Min(0) Long idPhoneNumbers) {
        phoneService.deletePhoneNumbers(idPerson, idPhoneNumbers);
    }

    @PostMapping
    public void addPhoneNumbers(@PathVariable @Min(0) Long idPerson,
                                @Valid @RequestBody PhoneDTO phoneDTO) {
        phoneService.addPhoneNumbers(idPerson, phoneDTO);

    }

    @GetMapping()
    public List<PhoneDTO> getPhoneNumbers(@PathVariable @Min(0) Long idPerson) {
        return phoneService.getPhoneNumbers(idPerson);
    }


}
