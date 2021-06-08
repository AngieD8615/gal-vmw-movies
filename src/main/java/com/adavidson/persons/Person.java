package com.adavidson.persons;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long person_id;
    private String name;
    private Role role;
    private Long movie_id;

    enum Role {
        ACTOR, WRITER, DIRECTOR, CUSTUM_DESIGN, MUSICAL_COMPOSER, MAKE_UP
    }

    public Person() {}

    public Person(String name, Long movie_id, Role role) {
        this.name = name;
        this.movie_id = movie_id;
        this.role = role;
    }

}
