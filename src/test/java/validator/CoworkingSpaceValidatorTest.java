package validator;

import dao.CoworkingSpaceDao;
import exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CoworkingSpaceService;
import validation.BookingValidator;
import validation.CoworkingSpaceValidator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoworkingSpaceValidatorTest {
    private CoworkingSpaceValidator coworkingSpaceValidator;

    @BeforeEach
    public void prepareInstance() {coworkingSpaceValidator = new CoworkingSpaceValidator(new CoworkingSpaceService(new CoworkingSpaceDao()));}

    @Test
    void getValidatePrice_notCorrectInput_throwValidationException(){

        assertThrows(ValidationException.class, () -> coworkingSpaceValidator.validatePriceOfCoworkingFromUser(1));
    }

    @Test
    void getValidatePrice_correctInput_notThrowValidationException(){
        assertDoesNotThrow(() -> coworkingSpaceValidator.validatePriceOfCoworkingFromUser(12));
    }
}
