package course.validation;


import course.exception.ValidationException;
import course.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;

@Component
public class BookingValidator extends Validator {

    private final BookingService bookingService;

    @Autowired
    public BookingValidator(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public LocalDate validateDate(String dateStr) throws ValidationException {
        LocalDate date = null;
        String[] userInput;
        try {
            userInput = dateStr.split("\\.");
            date = LocalDate.of(Integer.parseInt(userInput[0]), Integer.parseInt(userInput[1]), Integer.parseInt(userInput[2]));
            if(LocalDate.now().isAfter(date)) {
                throw new DateTimeException("Date before nowadays");
            }
            return date;
        } catch (Exception e) {
            throw new ValidationException("(Example:year.month.dayOfMonth)");
        }
    }

    public String validateStartAndEndOfBookingTime(String startAndDuration) throws ValidationException { //
        String startAndEndOfBookingTime = null;
        String[] userInput;
        int endOfBooking;
        try {
            userInput = startAndDuration.split(",");
            endOfBooking = Integer.parseInt(userInput[0]) + Integer.parseInt(userInput[1]);
            startAndEndOfBookingTime = userInput[0] + ":00-" + endOfBooking + ":00";
            return startAndEndOfBookingTime;
        } catch (Exception e) {
            throw new ValidationException("(18,2), 18 - time, 2 - duration");
        }
    }

    public Long validateIdBook(Long id) throws ValidationException {
        bookingService.getBookingById(id).orElseThrow(() -> new ValidationException(", booking with this id does not exist"));
        return id;
    }
}
