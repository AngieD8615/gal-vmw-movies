package com.adavidson.movies;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    DataService dataService;

    // constructor injection
    public MovieController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping()
    public List<Movie> getMovies () {
        return dataService.getMovies();
    }
    @PostMapping()
    public Movie addMovie (@RequestBody String title) {
        Movie newMovie = new Movie(title);
        return dataService.addMovie(newMovie);
    }
}
