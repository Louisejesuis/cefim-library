package fr.louiseroy.library.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.louiseroy.library.customer.model.Customer;
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
public class CustomerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Customer jeanDupont;

    @BeforeEach
    void setUp() {
        jeanDupont = new Customer("Jean", "Dupont", "jean.dupont@gmail.com", 25);
    }

    @Test
    void getAllCustomers() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/customer/all");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        List<Customer> customerList = Arrays.asList(objectMapper.readValue(contentAsString, Customer[].class));

        assert customerList.contains(jeanDupont);
    }

    @Test
    void getOneCustomer() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/customer/1");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        Customer customer = objectMapper.readValue(contentAsString, Customer.class);

        assert jeanDupont.equals(customer);
    }

    @Test
    void getCustomersByLastName() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/api/customer/filter?lastname=Dupont");
        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();
        String contentAsString = mockMvc.perform(request)
                .andExpect(resultStatus)
                .andReturn().getResponse().getContentAsString();

        List<Customer> customerList = Arrays.asList(objectMapper.readValue(contentAsString, Customer[].class));

        assert customerList.contains(jeanDupont);
    }
}
