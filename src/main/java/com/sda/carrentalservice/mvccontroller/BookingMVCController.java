package com.sda.carrentalservice.mvccontroller;

import com.sda.carrentalservice.entity.Booking;
import com.sda.carrentalservice.entity.Calculator;
import com.sda.carrentalservice.entity.Car;
import com.sda.carrentalservice.entity.Customer;
import com.sda.carrentalservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BookingMVCController {

    private final BookingService bookingService;
    private final BranchService branchService;
    private final CarService carService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final UserService userService;

    private final Calculator calculator;

    @Autowired
    public BookingMVCController(BookingService bookingService, BranchService branchService, CarService carService, EmployeeService employeeService, CustomerService customerService, UserService userService, Calculator calculator) {
        this.bookingService = bookingService;
        this.branchService = branchService;
        this.carService = carService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.userService = userService;
        this.calculator = calculator;
    }

    @GetMapping(path = "/bookings")
    public String showBooking(Model model) {
        model.addAttribute("bookings", this.bookingService.findAllBookings());
        model.addAttribute("bookingsNumber", this.bookingService.countBookings());
        model.addAttribute("sumOfAllBookings", this.bookingService.getSumOfAllBookingAmount());
        return "booking-list";
    }

    @GetMapping(path = "/booking/registration")
    public String showRegistration(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("allBranches", this.branchService.findAllBranches());
        model.addAttribute("allCars", this.carService.findAllCars());
        model.addAttribute("employees", this.employeeService.findAllEmployees());
        return "add-booking";
    }

    @PostMapping(path = "/")
    public String addBookingFromIndex(@ModelAttribute("booking") @Valid Booking booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index";
        } else {
            Customer customer = customerService.getCustomerLoggedIn();
            booking.setCustomer(customer);
            Car carById = carService.getCarById(booking);
            calculator.calculateAmountForBooking(booking, carById);
            this.bookingService.saveBooking(booking);
        }
        return "redirect:/";
    }

    @PostMapping(path = "/booking/add")
    public String addBooking(@ModelAttribute("booking") @Valid Booking booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-booking";
        } else {
            Customer customer = customerService.getCustomerLoggedIn();
            booking.setCustomer(customer);
            Car carById = carService.getCarById(booking);
            calculator.calculateAmountForBooking(booking, carById);
            this.bookingService.saveBooking(booking);
            return "redirect:/";
        }
    }

    @GetMapping(path = "/booking/delete/{id}")
    public String deleteBookingById(@PathVariable("id") Long id) {
        this.bookingService.deleteBookingById(id);
        return "redirect:/account/orders";
    }

    @PostMapping(path = "/booking/update")
    public String editBooking(@ModelAttribute("booking") @Valid Booking booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-booking";
        }
        Car carById = carService.getCarById(booking);
        calculator.calculateAmountForBooking(booking, carById);
        this.bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping(path = "/booking/edit/{id}")
    public String showEditPageBooking(@PathVariable("id") Long id, Model model) {
        model.addAttribute("booking", this.bookingService.findBookingById(id));
        model.addAttribute("branches", this.branchService.findAllBranches());
        model.addAttribute("cars", this.carService.findAllCars());
        model.addAttribute("employees", this.employeeService.findAllEmployees());
        return "edit-booking";
    }

    @GetMapping(path = "/order/booking/edit/{id}")
    public String showSettingsEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("booking", this.bookingService.findBookingById(id));
        model.addAttribute("branches", this.branchService.findAllBranches());
        model.addAttribute("cars", this.carService.findAllCars());
        model.addAttribute("employees", this.employeeService.findAllEmployees());
        return "order-edit";
    }

    @PostMapping(path = "/order/booking/update")
    public String editOrder(@ModelAttribute("booking") @Valid Booking booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "order-edit";
        }
        Car carById = carService.getCarById(booking);
        calculator.calculateAmountForBooking(booking, carById);
        this.bookingService.saveBooking(booking);
        return "redirect:/account/orders";
    }
}
