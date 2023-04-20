package fr.louiseroy.library.author.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"firstname", "lastname", "birthday"})
@ToString(of = {"id", "firstname", "lastname", "birthday"})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

    public Author(String firstname, String lastname, LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
    }

}
