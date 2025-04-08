package course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "course")
public class CoworkingReservationApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CoworkingReservationApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CoworkingReservationApp.class);
    }
}
