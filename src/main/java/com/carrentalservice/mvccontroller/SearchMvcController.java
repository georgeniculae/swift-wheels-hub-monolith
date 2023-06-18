package com.carrentalservice.mvccontroller;

import com.carrentalservice.dto.BookingDto;
import com.carrentalservice.dto.CarDto;
import com.carrentalservice.dto.SearchValueDto;
import com.carrentalservice.service.*;
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
        model.addAttribute("search", new SearchValueDto());
        model.addAttribute("booking", bookingService.findBookingByName(search));
        model.addAttribute("branch", branchService.findBranchByName(search));
        model.addAttribute("car", carService.findCarByName(search));
        model.addAttribute("customer", customerService.findCustomerByName(search));
        model.addAttribute("employee", employeeService.findEmployeeByName(search));
        model.addAttribute("rentalOffice", rentalOfficeService.findRentalOfficeByName(search));
        model.addAttribute("invoice", invoiceService.findInvoiceByName(search));

        ObjectError error = new ObjectError("search", "Nothing found");
        bindingResult.addError(error);

        return "index";
    }

    @GetMapping(path = "/")
    public String showSearch(Model model) {
        model.addAttribute("search", new SearchValueDto());
        model.addAttribute("booking", new BookingDto());
        model.addAttribute("allBookings", bookingService.findAllBookings());
        model.addAttribute("allBranches", branchService.findAllBranches());
        model.addAttribute("allCars", carService.findAllCars());
        model.addAttribute("car", new CarDto());
        model.addAttribute("allCustomers", customerService.findAllCustomer());
        model.addAttribute("allEmployees", employeeService.findAllEmployees());
        model.addAttribute("allRentalOffices", rentalOfficeService.findAllRentalOffices());
        model.addAttribute("allInvoicesCars", invoiceService.findAllInvoices());

        return "index";
    }

}