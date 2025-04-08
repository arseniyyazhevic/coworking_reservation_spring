package course.service;

import course.repository.BookingRepository;
import course.entity.Booking;
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
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
    @CacheEvict(value="bookings", key="id")
    public void cancelReservation(Long id) {
        bookingRepository.delete(getBookingById(id).orElseThrow());
    }

    @CachePut(value="bookings", key="#booking.id")
    public void makeReservation(Booking booking) {
        bookingRepository.save(booking);
    }

    public void viewMyReservations() {
        List<Booking> bookings = bookingRepository.findAll();
        System.out.printf("%-5s | %-20s | %-20s | %-15s | %-15s%n", "ID", "Customer Name", "Time Interval", "Booking Date", "Coworking Space");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (Booking booking : bookings) {
            System.out.printf("%-5d | %-20s | %-20s | %-15s | %-15s%n",
                    booking.getId(),
                    booking.getCustomerName(),
                    booking.getStartAndEndOfBookingTime(),
                    booking.getDate(),
                    booking.getCoworkingSpace().getName());
        }
    }
    @CachePut(value="bookings", key="#booking.id")
    public void updateBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Cacheable(value = "allBookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

}
