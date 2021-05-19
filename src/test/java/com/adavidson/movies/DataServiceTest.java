package com.adavidson.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DataServiceTest {
    private DataService dataService;

    @Mock
    MoviesRepository moviesRepository;

    @BeforeEach
    void setUp() {
        dataService = new DataService(moviesRepository);
    }

    @Test
    void getMovies() {
    }

    @Test
    void testGetMovies() {
    }

    @Test
    void addMovie() {
    }

    @Test
    void getMovieById() {
    }

    @Test
    void deleteMovieById() {
    }
}