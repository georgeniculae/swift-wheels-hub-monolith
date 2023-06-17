package com.carrentalservice.mvccontroller;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.service.BookingService;
import com.carrentalservice.service.BranchService;
import com.carrentalservice.service.CarService;
import com.carrentalservice.service.EmployeeService;
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
        model.addAttribute("booking", new BookingDto());
        model.addAttribute("allBranches", branchService.findAllBranches());
        model.addAttribute("allCars", carService.findAllCars());
        model.addAttribute("employees", employeeService.findAllEmployees());

        return "add-booking";
    }

    @PostMapping(path = "/")
    public String addBookingFromIndex(@ModelAttribute("booking") @Valid BookingDto booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        bookingService.saveBookingUpdatedWithCustomerAndCar(booking);

        return "redirect:/";
    }


    @PostMapping(path = "/booking/add")
    public String addBooking(@ModelAttribute("booking") @Valid BookingDto booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-booking";
        }

        bookingService.saveBookingUpdatedWithCustomerAndCar(booking);

        return "redirect:/";

    }

    @GetMapping(path = "/booking/delete/{id}")
    public String deleteBookingById(@PathVariable("id") Long id) {
        this.bookingService.deleteBookingById(id);

        return "redirect:/account/orders";
    }

    @PostMapping(path = "/booking/update")
    public String editBooking(@ModelAttribute("booking") @Valid BookingDto booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-booking";
        }

        bookingService.updateBooking(booking);

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
    public String editOrder(@ModelAttribute("booking") @Valid BookingDto booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "order-edit";
        }

        bookingService.updateBooking(booking);

        return "redirect:/account/orders";
    }

}
