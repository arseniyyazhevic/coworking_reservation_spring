package course.controller;

import course.entity.Booking;
import course.service.BookingService;
import course.service.CoworkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public String addBooking(@ModelAttribute Booking booking, @RequestParam("coworkingSpaceId") Long cowId) {
        booking.setCoworkingSpace(coworkingSpaceService.getCoworkingSpaceById(cowId).orElseThrow());
        bookingService.makeReservation(booking);
        return "redirect:/customer";
    }

    @GetMapping("/bookings") //TODO
    public String readBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings";
    }

    @GetMapping("/coworking-spaces") //TODO
    public String readCoworkingSpaces(Model model) {
        model.addAttribute("coworkings", coworkingSpaceService.getAllCoworkingSpaces());
        return "coworking-spaces";
    }

    @PostMapping("/update-booking")
    public String updateBooking(@RequestParam("id") Long id, @ModelAttribute Booking booking, @RequestParam("coworkingSpaceId") Long cowId){
        booking.setCoworkingSpace(coworkingSpaceService.getCoworkingSpaceById(cowId).orElseThrow());
        bookingService.updateBooking(id, booking);
        return "redirect:/customer";
    }

    @PostMapping("/cancel-booking")
    public String removeBooking(@RequestParam("id") Long id){
        bookingService.cancelReservation(id);
        return "redirect:/customer";
    }
}
