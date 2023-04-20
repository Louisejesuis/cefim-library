package fr.louiseroy.library.customer;

import fr.louiseroy.library.customer.model.Customer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new EntityNotFoundException("The customer id " + id + " doesn't exist");
    }

    public List<Customer> findCustomerByLastname(String lastname) {
        return customerRepository.findByLastnameContainingIgnoreCase(lastname);
    }
}
