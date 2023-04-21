package fr.louiseroy.library.book;

import fr.louiseroy.library.book.model.Book;
import fr.louiseroy.library.rating.model.BookRating;
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

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("{id}/rate")
    public BookRating getBookRating(@PathVariable Integer id) {
        return bookService.getAverageRateForBook(id);
    }

    @GetMapping("/rate/highest")
    public Book getHighestBookRating() {
        return bookService.getHighestRating();
    }
}
