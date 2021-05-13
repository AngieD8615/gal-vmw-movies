package com.adavidson.movies;

import java.util.List;

public class MovieList {
    private List<Movie> movies;
    private int size = 0;

    public MovieList() {
    }

    public MovieList(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getSize() {
        size = movies.size();
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return movies == null || movies.isEmpty();
    }
}
