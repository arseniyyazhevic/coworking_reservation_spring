package course.service;

import course.entity.BookingEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingEntityServiceTest {
    private BookingService bookingService;

    @BeforeEach
//    public void prepareTestInstance() {
//        bookingService = new BookingService(new BookingRepository());
//    }

    @Test
    void getBookingById_notFoundInMap_emptyOptional() {

        Optional<BookingEntity> booking = bookingService.getBookingById(100L);

        assertFalse(booking.isPresent());
    }
    @Test
    void getBookingById_FoundInMap_bookingFromFile(){
        Optional<BookingEntity> booking = bookingService.getBookingById(1L);

        assertTrue(booking.isPresent());
    }


}
