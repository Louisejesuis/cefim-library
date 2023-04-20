package fr.louiseroy.library.rating.model;

import fr.louiseroy.library.book.model.Book;
import fr.louiseroy.library.customer.model.Customer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rating")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"book", "customer", "comment", "rate"})
@ToString(of = {"id", "book", "customer", "comment", "rate"})
public class Rating {

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

    @Column(name = "comment")
    private String comment;

    @Column(name = "rate")
    private Integer rate;

    public Rating(Book book, Customer customer, String comment, Integer rate) {
        this.book = book;
        this.customer = customer;
        this.comment = comment;
        this.rate = rate;
    }

}
