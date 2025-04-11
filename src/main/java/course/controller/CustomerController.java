package course.controller;

import course.entity.BookingEntity;
import course.service.BookingService;
import course.service.CoworkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CoworkingSpaceService coworkingSpaceService;
    private final BookingService bookingService;

    @Autowired
    public CustomerController(CoworkingSpaceService coworkingSpaceService, BookingService bookingService) {
        this.coworkingSpaceService = coworkingSpaceService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public String showForm() {
        return "customer";
    }

    @PostMapping("/add-booking")
    public String addBooking(@ModelAttribute BookingEntity bookingEntity, @RequestParam("coworkingSpaceId") Long cowId) {
        bookingEntity.setCoworkingSpaceEntity(coworkingSpaceService.getCoworkingSpaceById(cowId).orElseThrow());
        bookingService.makeReservation(bookingEntity);
        return "redirect:/customer";
    }

    @GetMapping("/bookings")
    public String readBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings";
    }

    @GetMapping("/coworking-spaces")
    public String readCoworkingSpaces(Model model) {
        model.addAttribute("coworkings", coworkingSpaceService.getAllCoworkingSpaces());
        return "coworking-spaces";
    }

    @PostMapping("/update-booking")
    public String updateBooking(@RequestParam("id") Long id, @ModelAttribute BookingEntity bookingEntity, @RequestParam("coworkingSpaceId") Long cowId){
        bookingEntity.setCoworkingSpaceEntity(coworkingSpaceService.getCoworkingSpaceById(cowId).orElseThrow());
        bookingService.updateBooking(bookingEntity);
        return "redirect:/customer";
    }

    @PostMapping("/cancel-booking")
    public String removeBooking(@RequestParam("id") Long id){
        bookingService.cancelReservation(id);
        return "redirect:/customer";
    }
}
