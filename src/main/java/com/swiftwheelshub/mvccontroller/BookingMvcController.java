package com.swiftwheelshub.mvccontroller;

import com.swiftwheelshub.dto.BookingRequest;
import com.swiftwheelshub.service.BookingService;
import com.swiftwheelshub.service.BranchService;
import com.swiftwheelshub.service.CarService;
import com.swiftwheelshub.service.EmployeeService;
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
public class BookingMvcController {

    private final BookingService bookingService;
    private final BranchService branchService;
    private final CarService carService;
    private final EmployeeService employeeService;

    @GetMapping(path = "/bookings")
    public String showBooking(Model model) {
        model.addAttribute("bookings", bookingService.findAllBookings());
        model.addAttribute("bookingsNumber", bookingService.countBookings());
        model.addAttribute("sumOfAllBookings", bookingService.getSumOfAllBookingAmount());

        return "booking-list";
    }

    @GetMapping(path = "/booking/registration")
    public String showRegistration(Model model) {
        model.addAttribute("booking", new BookingRequest());
        model.addAttribute("allBranches", branchService.findAllBranches());
        model.addAttribute("allCars", carService.findAllCars());
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("currentDate", bookingService.getCurrentDate());

        return "add-booking";
    }

    @PostMapping(path = "/")
    public String addBookingFromIndex(@ModelAttribute("booking") @Valid BookingRequest booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        bookingService.saveBooking(booking);

        return "redirect:/";
    }

    @PostMapping(path = "/booking/add")
    public String addBooking(@ModelAttribute("booking") @Valid BookingRequest booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-booking";
        }

        bookingService.saveBooking(booking);

        return "redirect:/";

    }

    @GetMapping(path = "/booking/delete/{id}")
    public String deleteBookingById(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);

        return "redirect:/account/orders";
    }

    @PostMapping(path = "/booking/update")
    public String editBooking(@ModelAttribute("booking") @Valid BookingRequest booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-booking";
        }

        bookingService.updateBooking(null, booking);

        return "redirect:/bookings";
    }

    @GetMapping(path = "/booking/edit/{id}")
    public String showEditPageBooking(@PathVariable("id") Long id, Model model) {
        model.addAttribute("booking", bookingService.findBookingById(id));
        model.addAttribute("branches", branchService.findAllBranches());
        model.addAttribute("cars", carService.findAllCars());
        model.addAttribute("employees", employeeService.findAllEmployees());

        return "edit-booking";
    }

    @GetMapping(path = "/order/booking/edit/{id}")
    public String showSettingsEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("booking", bookingService.findBookingById(id));
        model.addAttribute("branches", branchService.findAllBranches());
        model.addAttribute("cars", carService.findAllCars());
        model.addAttribute("employees", employeeService.findAllEmployees());

        return "order-edit";
    }

    @PostMapping(path = "/order/booking/update")
    public String editOrder(@ModelAttribute("booking") @Valid BookingRequest booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "order-edit";
        }

        bookingService.updateBooking(null, booking);

        return "redirect:/account/orders";
    }

}
