package validator;

import course.repository.BookingRepository;
import course.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import course.service.BookingService;
import course.validation.BookingValidator;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class BookingEntityValidatorTest {

    private BookingValidator bookingValidator;

    @BeforeEach
//    public void prepareInstance() {bookingValidator = new BookingValidator(new BookingService(new BookingRepository()));}

    @Test
    void getValidateDate_notCorrectInput_throwValidationException(){

        assertThrows(ValidationException.class, () -> bookingValidator.validateDate("121.2121.21212.2112"));
    }
    @Test
    void getValidateDate_correctInput_notThrowValidationException(){
        assertDoesNotThrow(() -> bookingValidator.validateDate("2025.03.30"));
    }
}
