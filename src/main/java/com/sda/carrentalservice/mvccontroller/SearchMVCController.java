package com.sda.carrentalservice.mvccontroller;

import com.sda.carrentalservice.dto.SearchValueDTO;
import com.sda.carrentalservice.entity.Booking;
import com.sda.carrentalservice.entity.Car;
import com.sda.carrentalservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchMVCController {

    private final BookingService bookingService;
    private final BranchService branchService;
    private final CarService carService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final RentalOfficeService rentalOfficeService;
    private final RentalService rentalService;
    private final ReturnCarService returnCarService;
    private final RevenueService revenueService;

    @Autowired
    public SearchMVCController(BookingService bookingService, BranchService branchService, CarService carService, CustomerService customerService, EmployeeService employeeService, RentalService rentalService, RentalOfficeService rentalOfficeService, ReturnCarService returnCarService, RevenueService revenueService) {
        this.bookingService = bookingService;
        this.branchService = branchService;
        this.carService = carService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.rentalService = rentalService;
        this.rentalOfficeService = rentalOfficeService;
        this.returnCarService = returnCarService;
        this.revenueService = revenueService;
    }

    @PostMapping(path = "/search")
    public String search(@ModelAttribute("search") String search, Model model, BindingResult bindingResult) {
        model.addAttribute("search", new SearchValueDTO());
        model.addAttribute("booking", this.bookingService.findBookingByName(search));
        model.addAttribute("branch", this.branchService.findBranchByName(search));
        model.addAttribute("car", this.carService.findCarByName(search));
        model.addAttribute("customer", this.customerService.findCustomerByName(search));
        model.addAttribute("employee", this.employeeService.findEmployeeByName(search));
        model.addAttribute("rental", this.rentalService.findRentalByName(search));
        model.addAttribute("rentalOffice", this.rentalOfficeService.findRentalOfficeByName(search));
        model.addAttribute("returnCar", this.returnCarService.findReturnCarByName(search));
        ObjectError error = new ObjectError("search", "Nothing found!");
        bindingResult.addError(error);
        return "index";
    }

    @GetMapping(path = "/")
    public String showSearch(Model model) {
        model.addAttribute("search", new SearchValueDTO());
        model.addAttribute("booking", new Booking());
        model.addAttribute("allBookings", this.bookingService.findAllBookings());
        model.addAttribute("allBranches", this.branchService.findAllBranches());
        model.addAttribute("allCars", this.carService.findAllCars());
        model.addAttribute("car", new Car());
        model.addAttribute("allCustomers", this.customerService.findAllCustomer());
        model.addAttribute("allEmployees", this.employeeService.findAllEmployees());
        model.addAttribute("allRentals", this.rentalService.findAllRentals());
        model.addAttribute("allRentalOffices", this.rentalOfficeService.findAllRentalOffices());
        model.addAttribute("allReturnCars", this.returnCarService.findAllReturnCar());
        return "index";
    }
}