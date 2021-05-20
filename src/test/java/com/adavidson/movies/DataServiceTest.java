package com.adavidson.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
        Movie movie = new Movie("thisMove", "thisDirector", 2020);
        when(moviesRepository.findAll()).thenReturn(Arrays.asList(movie));
        MovieList movieList = dataService.getMovies();
        assertThat(movieList).isNotNull();
    }

    @Test
    void getMovies_withParams_actorOrTitle() {
        Movie movie = new Movie("thisMove", "thisDirector", 2020);
        when(moviesRepository.findByDirectorContainsAndTitleContains(anyString(), anyString()))
                .thenReturn(Arrays.asList(movie));
        MovieList movieList = dataService.getMovies("actor", "thisMovie");
        assertThat(movieList).isNotNull();
    }

    @Test
    void addMovie() {
        Movie movie = new Movie("thisMove", "thisDirector", 2020);
        Movie returnedMovie = new Movie("thisMove", "thisDirector", 2020);
        returnedMovie.setMovie_id(99L);
        when(moviesRepository.save(movie)).thenReturn(returnedMovie);
        Movie actReturnedMovie = dataService.addMovie(movie);
        assertThat(actReturnedMovie).isNotNull();
        assertThat(actReturnedMovie.getTitle()).isEqualTo(returnedMovie.getTitle());
    }

    @Test
    void getMovieById() {
        Movie movie = new Movie("thisMove", "thisDirector", 2020);
        when(moviesRepository.findByDirectorContainsAndTitleContains(anyString(), anyString()))
                .thenReturn(Arrays.asList(movie));
        MovieList movieList = dataService.getMovies("actor", "thisMovie");
        assertThat(movieList).isNotNull();
    }

    @Test
    void deleteMovieById() {
    }
}