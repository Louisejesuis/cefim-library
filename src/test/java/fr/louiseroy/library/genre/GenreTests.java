package fr.louiseroy.library.genre;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.louiseroy.library.genre.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Genre roman;

    @BeforeEach
    void setUp() {
        roman = new Genre("Roman");
    }

    @Test
    void getAllCustomers() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/genre/all");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        List<Genre> genreList = Arrays.asList(objectMapper.readValue(contentAsString, Genre[].class));

        assert genreList.contains(roman);
    }

    @Test
    void getOneGenre() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/genre/6");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        Genre genre = objectMapper.readValue(contentAsString, Genre.class);

        assert roman.equals(genre);
    }

    @Test
    void getGenresByName() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/genre/filter?name=Roman");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        List<Genre> genreList = Arrays.asList(objectMapper.readValue(contentAsString, Genre[].class));

        assert genreList.contains(roman);
    }
}
