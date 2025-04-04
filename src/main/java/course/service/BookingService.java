package course.service;

import course.repository.BookingRepository;
import course.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public Optional<Booking> getBookingById(Long id) {
        return Optional.ofNullable(bookingRepository.getBookingById(id));
    }

    public void cancelReservation(Long id) {
        bookingRepository.deleteBooking(id);
    }

    public void makeReservation(Booking booking) {
        bookingRepository.saveBooking(booking);
    }

    public void viewMyReservations() {
        List<Booking> bookings = bookingRepository.getAllBookings();
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

    public void updateBooking(Long id, Booking booking) {
        bookingRepository.updateBooking(booking, id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

}
