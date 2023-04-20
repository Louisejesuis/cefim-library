package fr.louiseroy.library.booking;

import fr.louiseroy.library.booking.model.Booking;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getBookingList() {
        return bookingRepository.findAll();
    }

    public Booking findBookingById(Integer id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            return booking.get();
        }
        throw new EntityNotFoundException("The booking id " + id + " doesn't exist");
    }
}
