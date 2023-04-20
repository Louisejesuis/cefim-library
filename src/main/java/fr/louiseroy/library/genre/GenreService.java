package fr.louiseroy.library.genre;

import fr.louiseroy.library.genre.model.Genre;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getGenreList() {
        return genreRepository.findAll();
    }

    public Genre findGenreById(Integer id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            return genre.get();
        }
        throw new EntityNotFoundException("The genre id " + id + " doesn't exist");
    }

    public List<Genre> findGenreByName(String name) {
        return genreRepository.findByNameContainingIgnoreCase(name);
    }
}
