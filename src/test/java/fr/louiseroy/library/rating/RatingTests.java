package fr.louiseroy.library.rating;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.louiseroy.library.author.model.Author;
import fr.louiseroy.library.book.State;
import fr.louiseroy.library.book.model.Book;
import fr.louiseroy.library.customer.model.Customer;
import fr.louiseroy.library.genre.model.Genre;
import fr.louiseroy.library.rating.model.Rating;
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
public class RatingTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Book lePetitPrince;

    private Customer antoineBernard;
    private Rating rating;

    @BeforeEach
    void setUp() {
        lePetitPrince = new Book(
                "Le Petit Prince",
                "Un aviateur tombe en panne dans le desert du Sahara. La, il rencontre un petit garcon",
                96,
                State.NEUF,
                new Genre("Roman"),
                new Author(
                        "Antoine",
                        "de Saint-Exupery",
                        LocalDate.of(1900, 6, 29)
                )
        );
        antoineBernard = new Customer("Antoine", "Bernard", "antoine.bernard@yahoo.fr", 45);
        rating = new Rating(lePetitPrince, antoineBernard, "J'ai beaucoup aime ce livre", 8);

    }

    @Test
    void getAllRatings() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/rating/all");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        List<Rating> ratingList = Arrays.asList(objectMapper.readValue(contentAsString, Rating[].class));

        assert ratingList.contains(rating);
    }

    @Test
    void getOneRating() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/rating/15");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        Rating ratingOfLePetitPrince = objectMapper.readValue(contentAsString, Rating.class);

        assert rating.equals(ratingOfLePetitPrince);
    }
}
