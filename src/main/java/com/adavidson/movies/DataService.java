package com.adavidson.movies;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataService {
    MoviesRepository moviesRepository;

    public DataService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public MovieList getMovies() {
        return new MovieList(moviesRepository.findAll());
    }

    public MovieList getMovies(String director, String title) {
        List<Movie> movies = moviesRepository.findByDirectorContainsAndTitleContains(director, title);
        if(!movies.isEmpty()) {
            return new MovieList(movies);
        }
        return null;
    }

    public Movie addMovie(Movie movie) {
        return moviesRepository.save(movie);
    }

    public Movie getMovieById(String movie_id) {
        return null;
    }

    public void deleteMovieById(String id) {

    }
}
