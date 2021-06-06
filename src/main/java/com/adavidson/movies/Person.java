package com.adavidson.movies;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "people")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long person_id;
    private String firstName;
    private String lastName;
    private Role role;

    enum Role {
        ACTOR, WRITER, DIRECTOR, CUSTUM_DESIGN, MUSICAL_COMPOSER, MAKE_UP
    }

    public Person() {}

    public Person(String firstName, String lastName, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
