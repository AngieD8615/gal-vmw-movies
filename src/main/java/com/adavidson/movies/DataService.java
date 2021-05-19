package com.adavidson.movies;

import org.springframework.stereotype.Service;



@Service
public class DataService {
    MoviesRepository moviesRepository;

    public DataService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public MovieList getMovies() {
        return null;
    }

    public MovieList getMovies(String actor, String title) {
        return null;
    }

    public Movie addMovie(Movie movie) {
        return null;
    }

    public Movie getMovieById(String movie_id) {
        return null;
    }

    public void deleteMovieById(String id) {

    }
}
