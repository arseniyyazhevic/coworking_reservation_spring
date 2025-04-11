package course.service;

import course.entity.BookingEntity;
import course.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Cacheable(value="bookings", key="#id")
    public Optional<BookingEntity> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
    @CacheEvict(value="bookings", key="id")
    public void cancelReservation(Long id) {
        bookingRepository.delete(getBookingById(id).orElseThrow());
    }

    @CachePut(value="bookings", key="#bookingEntity.id")
    public void makeReservation(BookingEntity bookingEntity) {
        bookingRepository.save(bookingEntity);
    }

    @CachePut(value="bookings", key="#bookingEntity.id")
    public void updateBooking(BookingEntity bookingEntity) {
        bookingRepository.save(bookingEntity);
    }

    @Cacheable(value = "allBookings")
    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

}
