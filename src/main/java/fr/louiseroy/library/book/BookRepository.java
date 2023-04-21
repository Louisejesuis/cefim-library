package fr.louiseroy.library.book;

import fr.louiseroy.library.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorId(int authorId);
}