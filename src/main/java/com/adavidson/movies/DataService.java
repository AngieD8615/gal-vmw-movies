package com.adavidson.movies;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public Movie getMovieById(Long movie_id) {
        return moviesRepository.findById(movie_id).orElse(null);
    }

    public void deleteMovieById(Long id) {
        Optional<Movie> oMovie = moviesRepository.findById(id);
        if (oMovie.isPresent()) {
            moviesRepository.delete(oMovie.get());
        } else {
            throw new InvalidMovieException();
        }
    }
}
