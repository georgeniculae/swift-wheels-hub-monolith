package com.sda.carrentalservice.mvccontroller;

import com.sda.carrentalservice.entity.Customer;
import com.sda.carrentalservice.service.BookingService;
import com.sda.carrentalservice.service.CustomerService;
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
public class CustomerMVCController {

    private final CustomerService customerService;
    private final BookingService bookingService;


    @Autowired
    public CustomerMVCController(CustomerService customerService, BookingService bookingService) {
        this.customerService = customerService;
        this.bookingService = bookingService;
    }

    @GetMapping(path = "/account/orders")
    public String showCurrentUserOrders(Model model) {
        Customer customerLoggedIn = customerService.getCustomerLoggedIn();
        model.addAttribute("orders", this.bookingService.findBookingByCustomerLoggedIn(customerLoggedIn));
        model.addAttribute("bookingsNumber", this.bookingService.countByCustomer(customerLoggedIn));
        model.addAttribute("totalAmountSpent", this.bookingService.calculateAllAmountSpentByUser(customerLoggedIn));
        return "order-list";
    }

    @GetMapping(path = "/settings")
    public String showSettingPage(Model model) {
        Customer customerLoggedIn = customerService.getCustomerLoggedIn();
        model.addAttribute("customer", customerLoggedIn);
        return "settings";
    }

    @PostMapping(path = "/settings/customer/update")
    public String editCurrentCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "settings";
        }
        getCustomerUpdate(customer);
        return "redirect:/";
    }

    @GetMapping(path = "/customers")
    public String showCustomers(Model model) {
        model.addAttribute("customers", this.customerService.findAllCustomer());
        model.addAttribute("customersNumber", this.customerService.countCustomers());
        return "customer-list";
    }

    @GetMapping(path = "/customer/registration")
    public String showAddCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }

    @PostMapping(path = "/customer/add")
    public String addCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-customer";
        } else {
            this.customerService.saveCustomer(customer);
            return "redirect:/customers";
        }
    }

    @GetMapping(path = "/customer/delete/{id}")
    public String deleteCustomerById(@PathVariable("id") Long id) {
        this.customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }

    @PostMapping(path = "/customer/update")
    public String editCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-customer";
        }
        getCustomerUpdate(customer);
        return "redirect:/customers";
    }

    private void getCustomerUpdate(@ModelAttribute("customer") @Valid Customer customer) {
        Customer customerUpdate = this.customerService.findCustomerById(customer.getId());
        if (customerUpdate != null) {
            customerUpdate.setFirstName(customer.getFirstName());
            customerUpdate.setLastName(customer.getLastName());
            customerUpdate.setEmail(customer.getEmail());
            customerUpdate.setAddress(customer.getAddress());
        }
        this.customerService.saveCustomer(customerUpdate);
    }

    @GetMapping(path = "/customer/edit/{id}")
    public String showEditPageCustomer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", this.customerService.findCustomerById(id));
        return "edit-customer";
    }
}
