package fr.louiseroy.library.genre;

import fr.louiseroy.library.genre.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findByNameContainingIgnoreCase(String name);

}