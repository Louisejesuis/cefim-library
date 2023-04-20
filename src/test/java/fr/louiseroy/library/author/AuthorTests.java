package fr.louiseroy.library.author;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.louiseroy.library.author.model.Author;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Author antoine;

    @BeforeEach
    void setUp() {
        antoine = new Author("Antoine", "de Saint-Exupery", LocalDate.of(1900, 6, 29));
    }

    @Test
    void getAllAuthors() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/author/all");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        List<Author> authorList = Arrays.asList(objectMapper.readValue(contentAsString, Author[].class));

        assert authorList.contains(antoine);
    }

    @Test
    void getOneAuthor() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/author/7");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        Author author = objectMapper.readValue(contentAsString, Author.class);

        assert author.equals(antoine);
    }

    @Test
    void getAuthorsByLastName() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/author/filter?lastname=de Saint-Exupery");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        List<Author> authorList = Arrays.asList(objectMapper.readValue(contentAsString, Author[].class));

        assert authorList.contains(antoine);
    }

}
