package fr.louiseroy.library.author;

import fr.louiseroy.library.author.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/all")
    public List<Author> getAllAuthors() {
        return authorService.getAuthorList();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Integer id) {
        return authorService.findAuthorById(id);
    }

    @GetMapping("/filter")
    public List<Author> getAuthorByLastName(@RequestParam String lastname) {
        return authorService.findAuthorByLastname(lastname);
    }

    @GetMapping("/{id}/books")
    public AuthorWithBooks getAuthorWithBooks(@PathVariable("id") Integer id) {
        return authorService.getAuthorWithBooks(id);
    }
}
