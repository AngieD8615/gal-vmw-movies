package com.adavidson.persons;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    PersonDataService personDataService;

    public PersonController(PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping()
    public ResponseEntity<PersonList> getPersons (@RequestParam(required = false) String name) {
        PersonList personList;
        if(name == null) {
            personList = personDataService.getPersons();
        } else {
            personList = personDataService.getPersons(name);
        }

        return personList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(personList);
    }
}
