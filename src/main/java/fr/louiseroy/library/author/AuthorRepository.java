package fr.louiseroy.library.author;

import fr.louiseroy.library.author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findByLastnameContainingIgnoreCase(String lastname);
}