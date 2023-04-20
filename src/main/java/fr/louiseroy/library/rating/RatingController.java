package fr.louiseroy.library.rating;

import fr.louiseroy.library.rating.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/all")
    public List<Rating> getAllRatings() {
        return ratingService.getRatingList();
    }

    @GetMapping("/{id}")
    public Rating getRatingById(@PathVariable Integer id) {
        return ratingService.findRatingById(id);
    }
}
