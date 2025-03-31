import dao.BookingDAO;
import dao.CoworkingSpaceDAO;
import service.BookingService;
import service.CoworkingSpaceService;
import service.UserService;
import ui.ConsoleInput;
import ui.MenuHandler;
import validation.BookingValidator;
import validation.CoworkingSpaceValidator;
import validation.UserValidator;

public class CoworkingReservationApp {

    private static final BookingService bookingService = new BookingService(new BookingDAO());
    private static final CoworkingSpaceService coworkingSpaceService = new CoworkingSpaceService(new CoworkingSpaceDAO());
    private static final UserService userService = new UserService();
    private static final ConsoleInput consoleInput = new ConsoleInput(new CoworkingSpaceValidator(coworkingSpaceService), new BookingValidator(bookingService), new UserValidator());
    private static final MenuHandler menuHandler = new MenuHandler(bookingService, coworkingSpaceService, userService, consoleInput);

    public static void main(String[] args) {
//        bookingService.loadBookingsFromDB();
//        coworkingSpaceService.loadCoworkingSpacesFromDB();
        menuHandler.processingReservationApp();
    }
}

