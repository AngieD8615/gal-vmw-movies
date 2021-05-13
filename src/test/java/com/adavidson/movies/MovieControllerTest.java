package com.adavidson.movies;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest
public class movieControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DataService dataService;

    List<Movie> movieList;

    @BeforeEach
    void setUp() {
        movieList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            movieList.add(new Movie("Title_" + i));
        }
    }

    @Test
    void getData() throws Exception {
        when(dataService.getMovies()).thenReturn(movieList);

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void addMovie() throws Exception {
        Movie movie = new Movie("New Movie");
        when(dataService.addMovie(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Movie\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("New Movie"));
    }

}
