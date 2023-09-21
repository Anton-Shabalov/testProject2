package com.example.testProject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddressDTO {
    private Long id;
    @Size(min = 2, max = 255, message = "The country cannot be shorter than 5 characters")
    private String country;
    @Size(min = 2, max = 255, message = "The country cannot be shorter than 5 characters")
    private String city;
    @Size(min = 2, max = 255, message = "The country cannot be shorter than 5 characters")
    private String street;
    @Min(value = 1, message = "number home there should be more 1")
    private Integer home;
    @Min(value = 1, message = "number apartment there should be more 1")
    private Integer apartment;
}
