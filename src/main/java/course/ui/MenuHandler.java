package course.ui;

import course.entity.Booking;
import course.entity.CoworkingSpace;
import course.entity.roles.Admin;
import course.entity.roles.Customer;
import course.entity.roles.User;
import course.service.BookingService;
import course.service.CoworkingSpaceService;
import course.service.UserService;
import course.ui.menus.AdminMenu;
import course.ui.menus.CustomerMenu;
import course.ui.menus.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuHandler {
    private final BookingService bookingService;
    private final CoworkingSpaceService coworkingSpaceService;
    private final UserService userService;
    private final ConsoleInput consoleInput;

    @Autowired
    public MenuHandler(BookingService bookingService, CoworkingSpaceService coworkingSpaceService, UserService userService, ConsoleInput consoleInput) {
        this.bookingService = bookingService;
        this.coworkingSpaceService = coworkingSpaceService;
        this.userService = userService;
        this.consoleInput = consoleInput;
    }


    public void processingReservationApp() {
        ConsoleOutput.println("Welcome to Coworking Space Reservation App – Your Gateway to Productive Workspaces!");
        User user = userService.getUserAndSetLogin(consoleInput);
        if (user == null) {
            System.exit(0);
        }
        Menu menu = createMenu(user);
        user = userService.createUser(user);
        if (user instanceof Admin) {
            handlingUserInputInAdminMenu((AdminMenu) menu);
        } else if (user instanceof Customer) {
            handlingUserInputInCustomerMenu((CustomerMenu) menu);
        }
    }

    private Menu createMenu(User userRole) {
        if (userRole instanceof Admin) {
            return new AdminMenu();
        } else {
            return new CustomerMenu();
        }
    }

    public void handlingUserInputInCustomerMenu(CustomerMenu menu) {
        while (true) {
            menu.showMenu();
            switch (consoleInput.getString("Make your choice: ")) {
                case "1" -> coworkingSpaceService.displayAllCoworkingSpaces();
                case "2" -> bookingService.makeReservation(createBookingUsingUserInput());
                case "3" -> {
                    bookingService.viewMyReservations();
                    bookingService.cancelReservation(consoleInput.getIdBook("Enter an id: "));
                }
                case "4" -> bookingService.viewMyReservations();
                case "5" -> {
                    bookingService.displayAllBookings();
                    bookingService.updateBooking(consoleInput.getIdBook("Enter an id to update: "), createBookingUsingUserInput());
                }
                case "6" -> processingReservationApp();
                default -> ConsoleOutput.println("Incorrect input");
            }
        }
    }

    public void handlingUserInputInAdminMenu(AdminMenu menu) {
        while (true) {
            menu.showMenu();
            switch (consoleInput.getString("")) {
                case "1" -> coworkingSpaceService.addCoworkingSpace(createCoworkingSpace());
                case "2" -> {
                    coworkingSpaceService.displayAllCoworkingSpaces();
                    coworkingSpaceService.removeCoworkingSpace(consoleInput.getIdCoworkingSpace("Enter and id: "));
                }
                case "3" -> {
                    coworkingSpaceService.displayAllCoworkingSpaces();
                    coworkingSpaceService.updateAllInformationAboutCoworkingSpace(consoleInput.getIdCoworkingSpace("Enter an id to update: "), createCoworkingSpace());
                }
                case "4" -> bookingService.displayAllBookings();
                case "5" -> processingReservationApp();
                default -> System.out.println("Incorrect input");
            }
        }
    }

    private Booking createBookingUsingUserInput() {
        Booking booking = new Booking();
        ConsoleOutput.println("Enter information about Booking");
        coworkingSpaceService.displayAllCoworkingSpaces();
        booking.setCoworkingSpace(coworkingSpaceService.getCoworkingSpaceById(consoleInput.getIdCoworkingSpace("Enter an id of Coworking space : ")).orElseThrow());
        booking.setCustomerName(consoleInput.getName("Enter your name: "));
        booking.setDate(consoleInput.getDate("Enter date (Example:year.month.dayOfMonth): "));
        booking.setStartAndEndOfBookingTime(consoleInput.getStartAndEndOfBookingTime("Enter time of reservation and duration (18,2), 18 - time, 2 - duration: "));
        return booking;
    }

    public CoworkingSpace createCoworkingSpace() {
        CoworkingSpace coworkingSpace = new CoworkingSpace();
        ConsoleOutput.println("Enter information about Coworking Space");
        coworkingSpace.setName(consoleInput.getName("Enter your name: "));
        coworkingSpace.setTypeOfWorkspaces(consoleInput.getTypeOfCoworkingSpace("Type of coworking (private/open space/room/meeting room): "));
        coworkingSpace.setPriceInDollars(consoleInput.getPrice("Price in dollars: "));
        coworkingSpace.setAvailabilityStatus(consoleInput.getAvailableStatus("Available status (false - not available/ true - available): "));
        return coworkingSpace;
    }


}
