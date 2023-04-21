package fr.louiseroy.library.book;

import fr.louiseroy.library.author.AuthorService;
import fr.louiseroy.library.book.model.Book;
import fr.louiseroy.library.genre.GenreService;
import fr.louiseroy.library.rating.model.BookRating;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    @Lazy
    private AuthorService authorService;

    @Autowired
    @Lazy
    private GenreService genreService;

    @Autowired
    private EntityManager entityManager;

    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    public Book findBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new EntityNotFoundException("The book id " + id + " doesn't exist");
    }

    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public Book createBook(Book book) {
        if (bookRepository.findAll().contains(book)) {
            throw new EntityExistsException("The book with the title " + book.getTitle() + " already exist");
        }
        if (authorService.getAuthorList().contains(book.getAuthor())) {
            book.setAuthor(authorService.getAuthorList().stream().filter(author -> author.equals(book.getAuthor())).toList().get(0));
        }

        if (genreService.getGenreList().contains(book.getGenre())) {
            book.setGenre(genreService.getGenreList().stream().filter(genre -> genre.equals(book.getGenre())).toList().get(0));
        }
        bookRepository.save(book);
        return book;
    }

    public List<Book> getBooksForAuthor(int authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public BookRating getAverageRateForBook(Integer bookId) {
        @SuppressWarnings("unchecked")
        List<Tuple> rate = entityManager.createNativeQuery("""
                            select avg(rate) as rate
                            from rating 
                            inner join book on book.id = rating.book_id
                            where book.id = :bookId
                            group by book.id;
                        """, Tuple.class)
                .setParameter("bookId", bookId)
                .getResultList();

        if (rate.isEmpty()) {
            return new BookRating();
        }

        return new BookRating(rate.get(0));
    }

    public Book getHighestRating() {
        List<Book> bookList = getBookList();

        Comparator<Book> byRatingDescending = (b1, b2) -> {
            BookRating r1 = getAverageRateForBook(b1.getId());
            BookRating r2 = getAverageRateForBook(b2.getId());
            return r2.compareTo(r1);
        };
        bookList.sort(byRatingDescending);
        return bookList.get(0);
    }

}
