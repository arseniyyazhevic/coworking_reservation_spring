package course.controller;

import course.entity.CoworkingSpaceEntity;
import course.service.BookingService;
import course.service.CoworkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CoworkingSpaceService coworkingSpaceService;
    private final BookingService bookingService;

    @Autowired
    public AdminController(CoworkingSpaceService coworkingSpaceService, BookingService bookingService) {
        this.coworkingSpaceService = coworkingSpaceService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public String showForm() {
        return "admin";
    }

    @PostMapping("/add-coworking")
    public String addCoworking(@ModelAttribute CoworkingSpaceEntity coworkingSpaceEntity) {
        coworkingSpaceService.addCoworkingSpace(coworkingSpaceEntity);
        return "redirect:/admin";
    }

    @PostMapping("/remove-coworking")
    public String removeCoworking(@RequestParam("id") Long id) {
        coworkingSpaceService.removeCoworkingSpace(id);
        return "redirect:/admin";
    }

    @PostMapping("/update-coworking")
    public String updateCoworking(@ModelAttribute CoworkingSpaceEntity coworkingSpaceEntity, @RequestParam("id") Long id) {
        coworkingSpaceService.updateAllInformationAboutCoworkingSpace(coworkingSpaceEntity);
        return "redirect:/admin";
    }

    @GetMapping("/bookings")
    public String readBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings";
    }
}
