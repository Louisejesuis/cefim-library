package fr.louiseroy.library.genre;

import fr.louiseroy.library.genre.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/all")
    public List<Genre> getAllGenres() {
        return genreService.getGenreList();
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Integer id) {
        return genreService.findGenreById(id);
    }

    @GetMapping("/filter")
    public List<Genre> getGenreByName(@RequestParam String name) {
        return genreService.findGenreByName(name);
    }
}
