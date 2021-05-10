package com.adavidson.movies;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    List<Movie> movieList = new ArrayList<>();
    int index = 0;

    public List<Movie> getMovies() {
        return movieList;
    }

    public Movie addMovie(Movie movie) {
        movie.setId(index++);
        movieList.add(movie);
        return movie;
    }
}
