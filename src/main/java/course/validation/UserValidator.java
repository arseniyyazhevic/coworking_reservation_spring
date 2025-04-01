package course.validation;

import course.entity.roles.Admin;
import course.entity.roles.Customer;
import course.entity.roles.User;
import course.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public User validateRoleInput(String userInput) throws ValidationException {
        if (userInput.equalsIgnoreCase("customer") || userInput.equals("1")) {
            return new Customer();
        } else if (userInput.equalsIgnoreCase("admin") || userInput.equals("2")) {
            return new Admin();
        } else {
            throw new ValidationException("Invalid unput (1/2)");
        }
    }

    public String validateUserLogin(String userLoginInput) throws ValidationException {
        if (userLoginInput.equals("3") || userLoginInput.equalsIgnoreCase("exit")) {
            return "exit";
        }
        if (userLoginInput.length() >= 5) {
            return userLoginInput;
        } else {
            throw new ValidationException("Invalid input (more than 5 characters)");
        }
    }
}
