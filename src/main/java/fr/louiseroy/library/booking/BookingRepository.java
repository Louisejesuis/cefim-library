package fr.louiseroy.library.booking;

import fr.louiseroy.library.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}