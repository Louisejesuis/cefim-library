package fr.louiseroy.library.author;

import fr.louiseroy.library.author.model.Author;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthorList() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(Integer id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get();
        }
        throw new EntityNotFoundException("The author id " + id + " doesn't exist");
    }

    public List<Author> findAuthorByLastname(String lastname) {
        return authorRepository.findByLastnameContainingIgnoreCase(lastname);
    }
}
