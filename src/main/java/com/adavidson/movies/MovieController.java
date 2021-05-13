package com.adavidson.movies;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    DataService dataService;

    // constructor injection
    public MovieController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping()
    public ResponseEntity<MovieList> getMovies (@RequestParam(required = false) String actor,
                                                @RequestParam(required = false) String title) {
        MovieList movieList;
        if (actor == null && title == null) {
            movieList = dataService.getMovies();
        } else {
            movieList = dataService.getMovies(actor, title);
        }

        return movieList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movieList);
    }

    @PostMapping()
    public Movie addMovie (@RequestBody String title) {
        Movie newMovie = new Movie(title);
        return dataService.addMovie(newMovie);
    }
}
