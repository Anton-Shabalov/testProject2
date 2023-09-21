package com.example.testProject.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@Data
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    @Size(min = 2, max = 20, message = "The name cannot be shorter than 2 characters and more than 20")
    private String name;
    @Size(min = 2, max = 20, message = "The surname cannot be shorter than 2 characters and more than 20")
    private String surname;
    @Valid
    private List<PhoneDTO> phones;
    @Valid
    private List<AddressDTO> addresses;
}
