package com.carrentalservice.mvccontroller;

import com.carrentalservice.entity.Rental;
import com.carrentalservice.service.BookingService;
import com.carrentalservice.service.EmployeeService;
import com.carrentalservice.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RentalMvcController {

    private final RentalService rentalService;
    private final EmployeeService employeeService;
    private final BookingService bookingService;

    @GetMapping(path = "/rentals")
    public String showRentals(Model model) {
        model.addAttribute("rentals", rentalService.findAllRentals());
        model.addAttribute("rentalsNumber", rentalService.countRental());

        return "rental-list";
    }

    @GetMapping(path = "/rental/delete/{id}")
    public String deleteRentalById(@PathVariable("id") Long id) {
        rentalService.deleteRentalById(id);

        return "redirect:/rentals";
    }

    @PostMapping(path = "/rental/add")
    public String addRental(@ModelAttribute("rental") @Valid Rental rental, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-rental";
        }

        rentalService.saveRental(rental);

        return "redirect:/rentals";
    }

    @GetMapping(path = "/rental/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("rental", new Rental());
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("bookings", bookingService.findAllBookings());

        return "add-rental";
    }

    @PostMapping(path = "/rental/update")
    public String editRental(@ModelAttribute("rental") @Valid Rental rental, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-rental";
        }

        rentalService.saveRental(rental);

        return "redirect:/rentals";
    }

    @GetMapping(path = "/rental/edit/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rental", rentalService.findRentalById(id));
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("bookings", bookingService.findAllBookings());

        return "edit-rental";
    }

}
