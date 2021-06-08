package com.adavidson.persons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PersonControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonDataService personDataService;

    List<Person> persons;

    @BeforeEach
    void setUp() {
        persons = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            persons.add(new Person("Jen Chen", 99l, Person.Role.ACTOR));
        }
    }
    @Test
    void getPersons_noArgs_exists_returnAllMovies() throws Exception {
        when(personDataService.getPersons()).thenReturn(new PersonList(persons));

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons", hasSize(10)));
    }

}
