package course.service;

import course.entity.roles.Admin;
import course.entity.roles.Customer;
import course.entity.roles.User;
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

}
