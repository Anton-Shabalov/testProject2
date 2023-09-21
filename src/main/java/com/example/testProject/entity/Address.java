package com.example.testProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String country;
    private String city;
    private String street;
    private Integer home;
    private Integer apartment;
    @JoinColumn(name = "person_id")
    @ManyToOne
    private Person person;


}
