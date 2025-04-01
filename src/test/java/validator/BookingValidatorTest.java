package validator;

import course.dao.BookingDAO;
import course.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import course.service.BookingService;
import course.validation.BookingValidator;

import static org.junit.jupiter.api.Assertions.*;

public class BookingValidatorTest {
    private BookingValidator bookingValidator;

    @BeforeEach
    public void prepareInstance() {bookingValidator = new BookingValidator(new BookingService(new BookingDAO()));}

    @Test
    void getValidateDate_notCorrectInput_throwValidationException(){

        assertThrows(ValidationException.class, () -> bookingValidator.validateDate("121.2121.21212.2112"));
    }
    @Test
    void getValidateDate_correctInput_notThrowValidationException(){
        assertDoesNotThrow(() -> bookingValidator.validateDate("2025.03.30"));
    }
}
