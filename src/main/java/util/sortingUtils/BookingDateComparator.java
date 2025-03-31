package util.sortingUtils;

import entity.Booking;

import java.util.Comparator;

public class BookingDateComparator implements Comparator<Booking> {
    @Override
    public int compare(Booking b1, Booking b2) {
        return b1.getDate().compareTo(b2.getDate());
    }
}
