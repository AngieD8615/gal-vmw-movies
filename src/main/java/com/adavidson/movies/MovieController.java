package com.adavidson.movies;

import org.springframework.http.HttpStatus;
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
    public Movie addMovie (@RequestBody(required = false) Movie data) {
        Movie newMovie = new Movie(data.getTitle(), data.getDirector(), data.getYear());
        return dataService.addMovie(newMovie);
    }

    @GetMapping("/{movie_id}")
    public ResponseEntity<Movie> getMovieById (@PathVariable String movie_id) {
        return dataService.getMovieById(movie_id) == null? ResponseEntity.noContent().build() : ResponseEntity.ok(dataService.getMovieById(movie_id));
    }

    @DeleteMapping("/{movie_id}")
    public ResponseEntity deleteMovieById (@PathVariable String movie_id) {
        try {
            dataService.deleteMovieById(movie_id);
        } catch (InvalidMovieException e){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.accepted().build();
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void invalidMovieExceptionHandler(InvalidMovieException e) {
    }

}
