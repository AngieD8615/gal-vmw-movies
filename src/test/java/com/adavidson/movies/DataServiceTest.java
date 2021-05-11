package com.adavidson.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(25, dataService.getMovies().size());
    }
}
