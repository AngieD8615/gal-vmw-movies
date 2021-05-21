package com.adavidson.movies;


import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest
public class MovieControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DataService dataService;

    List<Movie> movies;

    ObjectMapper mapper = new ObjectMapper();
    Movie mappedMovie = new Movie("Movie Name", "person", 2020);

    @BeforeEach
    void setUp() {
        movies = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            movies.add(new Movie("Title_" + i));
        }
    }

    @Test
    void getMovies_noArgs_exists_returnAllMovies() throws Exception {
        when(dataService.getMovies()).thenReturn(new MovieList(movies));

        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movies", hasSize(10)));
    }

    @Test
    void getMovies_noArgs_noneExist_returnNoContext() throws Exception {
        when(dataService.getMovies()).thenReturn(new MovieList());

        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getMovies_withSearchParam_returnListOfMatchingMovies() throws Exception {
        when(dataService.getMovies(anyString(), anyString())).thenReturn(new MovieList(movies));

        mockMvc.perform(get("/api/movies?director=person&title=thisMovie"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movies", hasSize(10)));
    }

    @Test
    void addMovie_successfullyCreated_returnsMovie() throws Exception {
        Movie movie = new Movie("Movie Name", "person", 2020);
        when(dataService.addMovie(any(Movie.class))).thenReturn(movie);
        String json = mapper.writeValueAsString(movie);
        movie.setMovie_id(100L);
        mockMvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Movie Name"))
                .andExpect(jsonPath("year").value(2020));
    }

    @Test
    void addMovie_badRequest_returnsInvalidException() throws Exception {
        when(dataService.addMovie(any(Movie.class))).thenThrow(InvalidMovieException.class);

        mockMvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"foo\":\"bar\"}"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void getMovieById_returnsMove() throws Exception {
        Movie movie = new Movie("Movie Name", "person", 2020);
        when(dataService.getMovieById(anyLong())).thenReturn(movie);

        mockMvc.perform(get("/api/movies/" + anyLong()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Movie Name"));
    }

    @Test
    void getMovieById_IdNotValid_returnsNoContent() throws Exception {
        when(dataService.getMovieById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/api/movies/" + anyLong()))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteMovieById_valid_returnsAccepted() throws Exception {
        mockMvc.perform(delete("/api/movies/" + anyLong()))
                .andExpect(status().isAccepted());
        verify(dataService).deleteMovieById(anyLong());
    }

    @Test
    void deleteMovie_validId_doesNotExist_returnNoContent() throws Exception {
        doThrow(new InvalidMovieException()).when(dataService).deleteMovieById(anyLong());
        mockMvc.perform(delete("/api/movies/" + anyLong()))
                .andExpect(status().isNoContent());
    }
}
