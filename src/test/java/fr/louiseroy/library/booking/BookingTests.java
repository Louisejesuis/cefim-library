package fr.louiseroy.library.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.louiseroy.library.author.model.Author;
import fr.louiseroy.library.book.State;
import fr.louiseroy.library.book.model.Book;
import fr.louiseroy.library.booking.model.Booking;
import fr.louiseroy.library.customer.model.Customer;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Book lePetitPrince;
    private Customer antoineBernard;
    private Booking bookingOfLePetitPrince;

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
        bookingOfLePetitPrince = new Booking(lePetitPrince, antoineBernard, LocalDate.of(2023, 4, 18));

    }

    @Test
    void getAllBookings() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/booking/all");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        List<Booking> bookingList = Arrays.asList(objectMapper.readValue(contentAsString, Booking[].class));

        assert bookingList.contains(bookingOfLePetitPrince);
    }

    @Test
    void getOneBooking() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/booking/11");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        Booking booking = objectMapper.readValue(contentAsString, Booking.class);

        assert bookingOfLePetitPrince.equals(booking);
    }
}