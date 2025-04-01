package course.service;

import course.entity.roles.Admin;
import course.entity.roles.Customer;
import course.entity.roles.User;
import course.ui.ConsoleInput;
import course.ui.ConsoleOutput;
import org.springframework.stereotype.Service;

@Service
public class UserService {
//    public static HashSet<User> allUsers = new HashSet<>();

    public User createUser(User userRole) {
        if (userRole instanceof Admin) {
            return new Admin();
        } else {
            return new Customer();
        }
    }

    public User getUserAndSetLogin(ConsoleInput consoleInput) {
        User user = consoleInput.getUserRoleInput("Please choose your role Customer(1) or Admin(2): ");
        String userLoginInput = consoleInput.getUserLoginInput(("If you want to Exit enter 3 or write down your login (five and more characters): "));
        if (userLoginInput.equals("exit") || userLoginInput.equals("3")) {
            return null;
        }
        ConsoleOutput.println(userLoginInput);
        user.setLogin(userLoginInput);
        return user;
    }

}
