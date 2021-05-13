package com.adavidson.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DataServiceTest {

    DataService dataService;

    List<Movie> testData;
    @BeforeEach
    void setUp() {
        testData = new ArrayList<>();
        dataService = new DataService();
        for (int i = 0; i < 25; i++) {
            testData.add(dataService.addMovie(new Movie("movie-"+i)));
        }
    }
    @Test
    void getMovies() {
    }
}
