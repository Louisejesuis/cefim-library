package fr.louiseroy.library.customer;

import fr.louiseroy.library.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getCustomerList();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/filter")
    public List<Customer> getCustomerByLastName(@RequestParam String lastname) {
        return customerService.findCustomerByLastname(lastname);
    }
}
