package com.example.testProject.controller;

import com.example.testProject.dto.PersonDTO;
import com.example.testProject.service.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
    private final PersonService personService;


    @GetMapping()
    public List<PersonDTO> getAllPerson() {
        return personService.getAllPerson();

    }

    @PostMapping()
    public void addPerson(@Valid @RequestBody PersonDTO personDTO) {
        personService.addPerson(personDTO);
    }

    @GetMapping("/{idPerson}")
    public PersonDTO getPerson(@PathVariable @Min(0) Long idPerson) {
        return personService.getPerson(idPerson);
    }

    @PutMapping("/{idPerson}")
    public void updatePerson(@Valid @RequestBody PersonDTO personDTO,
                             @PathVariable @Min(0) Long idPerson) {
        personService.updatePerson(idPerson, personDTO);
    }

    @DeleteMapping("/{idPerson}")
    public void deletePreson(@PathVariable @Min(0) Long idPerson) {
        personService.deletePerson(idPerson);

    }


}
