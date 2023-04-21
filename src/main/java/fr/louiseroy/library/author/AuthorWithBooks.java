package fr.louiseroy.library.author;

import fr.louiseroy.library.author.model.Author;
import fr.louiseroy.library.book.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorWithBooks {

    private Author author;
    
    private List<Book> books;
}
