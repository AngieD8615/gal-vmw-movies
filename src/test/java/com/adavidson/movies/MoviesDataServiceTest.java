package com.adavidson.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoviesDataServiceTest {
    private MoviesDataService moviesDataService;

    @Mock
    MoviesRepository moviesRepository;

    @BeforeEach
    void setUp() {
        moviesDataService = new MoviesDataService(moviesRepository);
    }

    @Test
    void getMovies() {
        Movie movie = new Movie("thisMove", "thisDirector", 2020);
        when(moviesRepository.findAll()).thenReturn(Arrays.asList(movie));
        MovieList movieList = moviesDataService.getMovies();
        assertThat(movieList).isNotNull();
    }

    @Test
    void getMovies_withParams_directorOrTitle() {
        Movie movie = new Movie("thisMove", "thisDirector", 2020);
        when(moviesRepository.findByDirectorContainsAndTitleContains(anyString(), anyString()))
                .thenReturn(Arrays.asList(movie));
        MovieList movieList = moviesDataService.getMovies("thisDirector", "thisMovie");
        assertThat(movieList).isNotNull();
    }

    @Test
    void addMovie() {
        Movie movie = new Movie("thisMove", "thisDirector", 2020);
        Movie returnedMovie = new Movie("thisMove", "thisDirector", 2020);
        returnedMovie.setMovie_id(99L);
        when(moviesRepository.save(movie)).thenReturn(returnedMovie);
        Movie actReturnedMovie = moviesDataService.addMovie(movie);
        assertThat(actReturnedMovie).isNotNull();
        assertThat(actReturnedMovie.getTitle()).isEqualTo(returnedMovie.getTitle());
    }

    @Test
    void getMovieById() {
        Movie movie = new Movie("thisMove", "thisDirector", 2020);
        when(moviesRepository.findById(99L))
                .thenReturn(java.util.Optional.of(movie));
        Movie actMovie = moviesDataService.getMovieById(99L);
        assertThat(actMovie).isNotNull();
    }

    @Test
    void deleteMovieById() {
        Movie movie = new Movie("thisMove", "thisDirector", 2020);
        movie.setMovie_id(99L);
        when(moviesRepository.findById(anyLong())).thenReturn(java.util.Optional.of(movie));

        moviesDataService.deleteMovieById(movie.getMovie_id());

        verify(moviesRepository).delete(any(Movie.class));
    }

    @Test
    void deleteMovieById_notExist() {
        when(moviesRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(InvalidMovieException.class)
                .isThrownBy(() -> {
                    moviesDataService.deleteMovieById(anyLong());
        });
    }
}