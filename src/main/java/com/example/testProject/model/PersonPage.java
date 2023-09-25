package com.example.testProject.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PersonPage {
    private int pageNumber = 0;
    private int pageSize = 20;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "surname";
}
