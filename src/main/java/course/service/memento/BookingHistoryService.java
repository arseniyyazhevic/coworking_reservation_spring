package course.service.memento;

import course.entity.BookingEntity;
import course.entity.BookingHistoryEntity;
import course.repository.BookingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookingHistoryService {
    private final BookingHistoryRepository bookingHistoryRepository;

    @Autowired
    public BookingHistoryService(BookingHistoryRepository bookingHistoryRepository) {
        this.bookingHistoryRepository = bookingHistoryRepository;
    }


    public void saveSnapshot(BookingEntity booking) {
        BookingHistoryEntity snapshot = new BookingHistoryEntity();
        snapshot.setBookingId(booking.getId());
        snapshot.setCustomerName(booking.getCustomerName());
        snapshot.setStartAndEndOfBookingTime(booking.getStartAndEndOfBookingTime());
        snapshot.setDate(booking.getDate());
        snapshot.setCoworkingSpaceEntity(booking.getCoworkingSpaceEntity());
        snapshot.setSavedAt(LocalDateTime.now());
        bookingHistoryRepository.save(snapshot);
    }

    public Optional<BookingHistoryEntity> getLastSnapshot(Long bookingId) {
        return bookingHistoryRepository.findByBookingIdOrderBySavedAtDesc(bookingId).stream().skip(1).findFirst();
    }
}
