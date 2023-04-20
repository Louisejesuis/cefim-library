package fr.louiseroy.library.rating;

import fr.louiseroy.library.rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}