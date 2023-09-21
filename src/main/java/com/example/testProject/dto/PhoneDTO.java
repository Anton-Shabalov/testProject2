package com.example.testProject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class PhoneDTO {
    private Long id;
    @NotBlank
    @Pattern(regexp = "^(8)(\\d{10})$")
    private String number;


}
