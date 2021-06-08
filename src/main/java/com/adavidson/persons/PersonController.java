package com.adavidson.persons;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    PersonDataService personDataService;

    public PersonController(PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping()
    public PersonList getPersons () {
        return personDataService.getPersons();
    }
}
