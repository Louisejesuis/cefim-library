package fr.louiseroy.library.rating;

import fr.louiseroy.library.rating.model.Rating;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getRatingList() {
        return ratingRepository.findAll();
    }

    public Rating findRatingById(Integer id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()) {
            return rating.get();
        }
        throw new EntityNotFoundException("The rating id " + id + " doesn't exist");
    }
}
