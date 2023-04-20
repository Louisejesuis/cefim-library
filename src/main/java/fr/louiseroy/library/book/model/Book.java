package fr.louiseroy.library.book.model;

import fr.louiseroy.library.author.model.Author;
import fr.louiseroy.library.book.State;
import fr.louiseroy.library.genre.model.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"title", "description", "nbPages", "genre", "author"})
@ToString(of = {"id", "title", "description", "nbPages", "genre", "author"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "pages_number")
    private Integer nbPages;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String title, String description, Integer nbPages, State state, Genre genre, Author author) {
        this.title = title;
        this.description = description;
        this.nbPages = nbPages;
        this.state = state;
        this.genre = genre;
        this.author = author;
    }
}
