package course.service;

import course.entity.BookingEntity;
import course.entity.BookingHistoryEntity;
import course.repository.BookingHistoryRepository;
import course.repository.BookingRepository;
import course.service.memento.BookingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final BookingHistoryService bookingHistoryService;
    @Autowired
    public BookingService(BookingRepository bookingRepository, BookingHistoryService bookingHistoryService) {
        this.bookingRepository = bookingRepository;
        this.bookingHistoryService = bookingHistoryService;
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


    public void updateBookingWithHistory(Long bookingId, String newCustomerName, String newInterval, LocalDate newDate) {
        BookingEntity booking = bookingRepository.findById(bookingId).orElseThrow();
        bookingHistoryService.saveSnapshot(booking);

        booking.setCustomerName(newCustomerName);
        booking.setStartAndEndOfBookingTime(newInterval);
        booking.setDate(newDate);
        bookingRepository.save(booking);
    }

    public void undoBookingChange(Long bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId).orElseThrow();
        Optional<BookingHistoryEntity> snapshotOpt = bookingHistoryService.getLastSnapshot(bookingId);

        if (snapshotOpt.isPresent()) {
            BookingHistoryEntity snapshot = snapshotOpt.get();
            booking.setCustomerName(snapshot.getCustomerName());
            booking.setStartAndEndOfBookingTime(snapshot.getStartAndEndOfBookingTime());
            booking.setDate(snapshot.getDate());
            booking.setCoworkingSpaceEntity(snapshot.getCoworkingSpaceEntity());
            bookingRepository.save(booking);
        }
    }

}
