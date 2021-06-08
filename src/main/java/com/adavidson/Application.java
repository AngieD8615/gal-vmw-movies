package com.adavidson;

import com.adavidson.movies.MoviesApplication;
import com.adavidson.persons.PersonsApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(PersonsApplication.class, args);
        SpringApplication.run(MoviesApplication.class, args);
    }

}