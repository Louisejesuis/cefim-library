package fr.louiseroy.library.genre.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genre")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
@ToString(of = {"id", "name"})
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    private String name;

    public Genre(String name) {
        this.name = name;
    }

}