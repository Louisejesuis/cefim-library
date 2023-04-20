package fr.louiseroy.library.book;

import fr.louiseroy.library.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getBookList();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/filter")
    public List<Book> getBookByLastName(@RequestParam String title) {
        return bookService.findBookByTitle(title);
    }
}
