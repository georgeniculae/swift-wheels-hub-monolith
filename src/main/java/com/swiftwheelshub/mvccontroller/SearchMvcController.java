package com.swiftwheelshub.mvccontroller;

import com.swiftwheelshub.dto.BookingRequest;
import com.swiftwheelshub.dto.CarRequest;
import com.swiftwheelshub.dto.SearchValue;
import com.swiftwheelshub.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SearchMvcController {

    private final BookingService bookingService;
    private final BranchService branchService;
    private final CarService carService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final RentalOfficeService rentalOfficeService;
    private final InvoiceService invoiceService;

    @PostMapping(path = "/search")
    public String search(@ModelAttribute("search") String search, Model model, BindingResult bindingResult) {
        model.addAttribute("search", new SearchValue());
        model.addAttribute("booking", bookingService.findBookingByDateOfBooking(search));
        model.addAttribute("branch", branchService.findBranchByFilter(search));
        model.addAttribute("car", carService.findCarByFilter(search));
        model.addAttribute("customer", customerService.findCustomerByFilter(search));
        model.addAttribute("employee", employeeService.findEmployeeByFilter(search));
        model.addAttribute("rentalOffice", rentalOfficeService.findRentalOfficeByName(search));
        model.addAttribute("invoice", invoiceService.findInvoiceByComments(search));

        ObjectError error = new ObjectError("search", "Nothing found");
        bindingResult.addError(error);

        return "index";
    }

    @GetMapping(path = "/")
    public String showSearch(Model model) {
        model.addAttribute("search", new SearchValue());
        model.addAttribute("booking", new BookingRequest());
        model.addAttribute("allBookings", bookingService.findAllBookings());
        model.addAttribute("allBranches", branchService.findAllBranches());
        model.addAttribute("allCars", carService.findAllCars());
        model.addAttribute("car", new CarRequest());
        model.addAttribute("allCustomers", customerService.findAllCustomers());
        model.addAttribute("allEmployees", employeeService.findAllEmployees());
        model.addAttribute("allRentalOffices", rentalOfficeService.findAllRentalOffices());
        model.addAttribute("allInvoicesCars", invoiceService.findAllInvoices());
        model.addAttribute("numberOfCars", carService.countCars());
        model.addAttribute("numberOfCustomers", customerService.countCustomersWithoutBaseUsers());
        model.addAttribute("customersWithBookings", bookingService.countCustomersWithBookings());
        model.addAttribute("currentDate", bookingService.getCurrentDate());

        return "index";
    }

}