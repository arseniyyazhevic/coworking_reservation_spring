package course;

import course.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import course.ui.MenuHandler;

public class CoworkingReservationApp {

    public static void main(String[] args) {
//        bookingService.loadBookingsFromDB();
//        coworkingSpaceService.loadCoworkingSpacesFromDB();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MenuHandler menuHandler = applicationContext.getBean(MenuHandler.class);
        menuHandler.processingReservationApp();
    }
}

