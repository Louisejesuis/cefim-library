package fr.louiseroy.library.booking.model;


import fr.louiseroy.library.book.model.Book;
import fr.louiseroy.library.customer.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"book", "customer", "date"})
@ToString(of = {"id", "book", "customer", "date"})
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    public Booking(Book book, Customer customer, LocalDate date) {
        this.book = book;
        this.customer = customer;
        this.date = date;
    }

}