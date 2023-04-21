package fr.louiseroy.library.author;

import fr.louiseroy.library.author.model.Author;
import fr.louiseroy.library.book.BookService;
import fr.louiseroy.library.book.model.Book;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

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

    public AuthorWithBooks getAuthorWithBooks(int authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            List<Book> listBooks = bookService.getBooksForAuthor(authorId);
            return new AuthorWithBooks(author.get(), listBooks);
        }
        throw new EntityNotFoundException("Author with ID %d not found".formatted(authorId));
    }
}
