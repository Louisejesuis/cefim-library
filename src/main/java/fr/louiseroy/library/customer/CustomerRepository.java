package fr.louiseroy.library.customer;

import fr.louiseroy.library.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByLastnameContainingIgnoreCase(String lastname);

}