package com.adavidson.movies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    MoviesDataService moviesDataService;

    // constructor injection
    public MovieController(MoviesDataService moviesDataService) {
        this.moviesDataService = moviesDataService;
    }

    @GetMapping()
    public ResponseEntity<MovieList> getMovies (@RequestParam(required = false) String director,
                                                @RequestParam(required = false) String title) {
        MovieList movieList;
        if (director == null && title == null) {
            movieList = moviesDataService.getMovies();
        } else {
            movieList = moviesDataService.getMovies(director, title);
        }
        return movieList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movieList);
    }

    @PostMapping()
    public Movie addMovie (@RequestBody(required = false) Movie data) {
        Movie newMovie = new Movie(data.getTitle(), data.getDirector(), data.getYear());
        return moviesDataService.addMovie(newMovie);
    }

    @GetMapping("/{movie_id}")
    public ResponseEntity<Movie> getMovieById (@PathVariable Long movie_id) {
        return moviesDataService.getMovieById(movie_id) == null? ResponseEntity.noContent().build() : ResponseEntity.ok(moviesDataService.getMovieById(movie_id));
    }

    @DeleteMapping("/{movie_id}")
    public ResponseEntity deleteMovieById (@PathVariable Long movie_id) {
        try {
            moviesDataService.deleteMovieById(movie_id);
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
