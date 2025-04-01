package course.service;

import course.dao.BookingDAO;
import course.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {

    private final BookingDAO bookingDao;
    @Autowired
    public BookingService(BookingDAO bookingDao) {
        this.bookingDao = bookingDao;
    }


    public Optional<Booking> getBookingById(Long id) {
        return Optional.ofNullable(bookingDao.getBookingById(id));
    }

    public void cancelReservation(Long id) {
        bookingDao.deleteBooking(id);
    }

    public void makeReservation(Booking booking) {
        bookingDao.saveBooking(booking);
    }

    public void viewMyReservations() {
        List<Booking> bookings = bookingDao.getAllBookings();
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
        bookingDao.updateBooking(booking, id);
    }

    public void displayAllBookings() {
        List<Booking> bookings = bookingDao.getAllBookings();
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

}
