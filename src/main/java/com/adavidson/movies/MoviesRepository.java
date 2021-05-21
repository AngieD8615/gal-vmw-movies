package com.adavidson.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long>{
    List<Movie> findByDirectorContainsAndTitleContains(String director, String title);
}
