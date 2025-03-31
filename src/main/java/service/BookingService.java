package service;

import dao.BookingDao;
import entity.Booking;
import util.BookingDBUtils;

import java.util.*;

public class BookingService {

    private final BookingDao bookingDao;
    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }


    public Optional<Booking> getBookingById(Long id) {
//        Optional.ofNullable(BookingDBUtils.getBooking(id))
        return Optional.ofNullable(bookingDao.getBookingById(id));
    }

    public void cancelReservation(Long id) {
//        BookingDBUtils.deleteBooking(id);
        bookingDao.deleteBooking(id);
    }

    public void makeReservation(Booking booking) {
//        BookingDBUtils.createBooking(booking);
        bookingDao.saveBooking(booking);
    }

    public void viewMyReservations() {
//        ConsoleOutput.println("List of Customer Reservations: ");
//        allBookingsByCoworking.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue(new BookingDateComparator()))
//                .forEach(entry -> ConsoleOutput.println(entry.getKey() +" "+ entry.getValue()));
        BookingDBUtils.readBookings();

    }

    public void updateBooking(Long id, Booking booking) {
        bookingDao.updateBooking(booking, id);
    }

    public void displayAllBookings() {
//        ConsoleOutput.println("List of Bookings: ");
//        allBookingsByCoworking.entrySet().stream().
//                sorted(Map.Entry.comparingByValue(new BookingDateComparator())).
//                forEach(entry -> ConsoleOutput.println(entry.getKey() +" "+ entry.getValue()));
        BookingDBUtils.readBookings();
    }

}
