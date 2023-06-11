package com.carrentalservice.mvccontroller;

import com.carrentalservice.dto.SearchValueDto;
import com.carrentalservice.entity.Booking;
import com.carrentalservice.entity.Car;
import com.carrentalservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchMvcController {

    private final BookingService bookingService;
    private final BranchService branchService;
    private final CarService carService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final RentalOfficeService rentalOfficeService;
    private final RentalService rentalService;
    private final ReturnCarService returnCarService;

    @Autowired
    public SearchMvcController(BookingService bookingService, BranchService branchService, CarService carService, CustomerService customerService, EmployeeService employeeService, RentalService rentalService, RentalOfficeService rentalOfficeService, ReturnCarService returnCarService) {
        this.bookingService = bookingService;
        this.branchService = branchService;
        this.carService = carService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.rentalService = rentalService;
        this.rentalOfficeService = rentalOfficeService;
        this.returnCarService = returnCarService;
    }

    @PostMapping(path = "/search")
    public String search(@ModelAttribute("search") String search, Model model, BindingResult bindingResult) {
        model.addAttribute("search", new SearchValueDto());
        model.addAttribute("booking", bookingService.findBookingByName(search));
        model.addAttribute("branch", branchService.findBranchByName(search));
        model.addAttribute("car", carService.findCarByName(search));
        model.addAttribute("customer", customerService.findCustomerByName(search));
        model.addAttribute("employee", employeeService.findEmployeeByName(search));
        model.addAttribute("rental", rentalService.findRentalByName(search));
        model.addAttribute("rentalOffice", rentalOfficeService.findRentalOfficeByName(search));
        model.addAttribute("returnCar", returnCarService.findReturnCarByName(search));

        ObjectError error = new ObjectError("search", "Nothing found");
        bindingResult.addError(error);

        return "index";
    }

    @GetMapping(path = "/")
    public String showSearch(Model model) {
        model.addAttribute("search", new SearchValueDto());
        model.addAttribute("booking", new Booking());
        model.addAttribute("allBookings", bookingService.findAllBookings());
        model.addAttribute("allBranches", branchService.findAllBranches());
        model.addAttribute("allCars", carService.findAllCars());
        model.addAttribute("car", new Car());
        model.addAttribute("allCustomers", customerService.findAllCustomer());
        model.addAttribute("allEmployees", employeeService.findAllEmployees());
        model.addAttribute("allRentals", rentalService.findAllRentals());
        model.addAttribute("allRentalOffices", rentalOfficeService.findAllRentalOffices());
        model.addAttribute("allReturnCars", returnCarService.findAllReturnCar());

        return "index";
    }
}