package com.carrentalservice.mvccontroller;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.service.BookingService;
import com.carrentalservice.service.CustomerService;
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
public class CustomerMvcController {

    private final CustomerService customerService;
    private final BookingService bookingService;

    @GetMapping(path = "/account/orders")
    public String showCurrentUserOrders(Model model) {
        model.addAttribute("orders", bookingService.findBookingByLoggedInCustomer());
        model.addAttribute("bookingsNumber", bookingService.countByLoggedInCustomer());
        model.addAttribute("totalAmountSpent", bookingService.getAmountSpentByLoggedInUser());

        return "order-list";
    }

    @GetMapping(path = "/settings")
    public String showSettingPage(Model model) {
        CustomerDto customerLoggedIn = customerService.getLoggedInCustomerDto();
        model.addAttribute("customer", customerLoggedIn);

        return "settings";
    }

    @PostMapping(path = "/settings/customer/update")
    public String editCurrentCustomer(@ModelAttribute("customer") @Valid CustomerDto customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "settings";
        }

        customerService.saveCustomer(customer);

        return "redirect:/";
    }

    @GetMapping(path = "/customers")
    public String showCustomers(Model model) {
        model.addAttribute("customers", customerService.findAllCustomers());
        model.addAttribute("customersNumber", customerService.countCustomersWithoutBaseUsers());

        return "customer-list";
    }

    @GetMapping(path = "/customer/registration")
    public String showAddCustomer(Model model) {
        model.addAttribute("customer", new CustomerDto());

        return "add-customer";
    }

    @PostMapping(path = "/customer/add")
    public String addCustomer(@ModelAttribute("customer") @Valid CustomerDto customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-customer";
        }

        customerService.saveCustomer(customer);

        return "redirect:/customers";
    }

    @GetMapping(path = "/customer/delete/{id}")
    public String deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);

        return "redirect:/customers";
    }

    @PostMapping(path = "/customer/update")
    public String editCustomer(@ModelAttribute("customer") @Valid CustomerDto customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-customer";
        }

        customerService.updateCustomer(customer);

        return "redirect:/customers";
    }

    @GetMapping(path = "/customer/edit/{id}")
    public String showEditPageCustomer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", customerService.findCustomerById(id));

        return "edit-customer";
    }

}
