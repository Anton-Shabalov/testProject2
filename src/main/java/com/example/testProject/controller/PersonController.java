package com.example.testProject.controller;

import com.example.testProject.dto.PersonDTO;
import com.example.testProject.entity.Person;
import com.example.testProject.model.PersonPage;
import com.example.testProject.model.PersonSeachCriteria;
import com.example.testProject.service.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
    private final PersonService personService;


    //    @GetMapping()
//    public List<PersonDTO> getAllPerson() {
//        return personService.getAllPerson();
//
//    }
    @GetMapping("")
    public ResponseEntity<Page<Person>> getAllPersonWithFilters(PersonPage personPage,
                                                                PersonSeachCriteria personSeachCriteria) {
        return new ResponseEntity<>(personService.getAllPersonWithFilters(personPage, personSeachCriteria),
                HttpStatus.OK);
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
