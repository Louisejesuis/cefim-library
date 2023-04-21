package fr.louiseroy.library.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.louiseroy.library.author.model.Author;
import fr.louiseroy.library.book.model.Book;
import fr.louiseroy.library.genre.model.Genre;
import fr.louiseroy.library.rating.model.BookRating;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookTests {

    private Book lePetitPrince;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Genre roman;
    private Author antoine;

    @BeforeEach
    void setUp() {
        roman = new Genre("Roman");
        antoine = new Author("Antoine", "de Saint-Exupery", LocalDate.of(1900, 6, 29));
        lePetitPrince = new Book(
                "Le Petit Prince",
                "Un aviateur tombe en panne dans le desert du Sahara. La, il rencontre un petit garcon",
                96,
                State.NEUF,
                roman,
                antoine
        );
    }

    @Test
    void getAllBooks() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/book/all");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        List<Book> bookList = Arrays.asList(objectMapper.readValue(contentAsString, Book[].class));

        assert bookList.contains(lePetitPrince);
    }

    @Test
    void getOneBook() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/book/11");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        Book book = objectMapper.readValue(contentAsString, Book.class);

        assert book.equals(lePetitPrince);
    }

    @Test
    void getBooksByTitle() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/book/filter?title=Le Petit Prince");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        List<Book> bookList = Arrays.asList(objectMapper.readValue(contentAsString, Book[].class));

        assert bookList.contains(lePetitPrince);
    }

    @Test
    void testCreateBook() throws Exception {
        Book book = new Book("Harry potter et l'ordre du phenix", "Sorcelerie", 200, State.ABIME, roman, antoine);
        RequestBuilder request = MockMvcRequestBuilders.post("/api/book/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book));

        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        Book newBook = objectMapper.readValue(contentAsString, Book.class);
        assert newBook.equals(book);
    }

    @Test
    void getBookRate() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/book/1/rate");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        BookRating bookRating = objectMapper.readValue(contentAsString, BookRating.class);
        assert bookRating.getRate().equals(new BigDecimal("8.5000"));
    }

    @Test
    void getHighestBookRate() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/book/rate/highest");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();
        Book book = objectMapper.readValue(contentAsString, Book.class);

        assert book.getTitle().equals("Anges et Demons");
    }
}
