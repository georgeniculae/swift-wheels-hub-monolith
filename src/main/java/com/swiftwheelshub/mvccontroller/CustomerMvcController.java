package com.swiftwheelshub.mvccontroller;

import com.swiftwheelshub.dto.RegisterRequest;
import com.swiftwheelshub.dto.UserUpdateRequest;
import com.swiftwheelshub.service.BookingService;
import com.swiftwheelshub.service.CustomerService;
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

    @GetMapping(path = "/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping(path = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new RegisterRequest());

        return "register";
    }

    @PostMapping(path = "/user/register")
    public String registerUser(@ModelAttribute("customer") @Valid RegisterRequest registerRequest,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        customerService.registerCustomer(registerRequest);

        return "login";
    }

    @GetMapping(path = "/account/orders")
    public String showCurrentUserOrders(Model model) {
        model.addAttribute("orders", bookingService.findBookingsByLoggedInCustomer());
        model.addAttribute("bookingsNumber", bookingService.countByLoggedInCustomer());
        model.addAttribute("totalAmountSpent", bookingService.getAmountSpentByLoggedInUser());

        return "order-list";
    }

    @GetMapping(path = "/settings")
    public String showSettingPage(Model model) {
        model.addAttribute("customer", customerService.getCurrentUser());

        return "settings";
    }

    @PostMapping(path = "/settings/customer/update")
    public String editCurrentCustomer(@ModelAttribute("customer") @Valid RegisterRequest customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "settings";
        }

        customerService.registerCustomer(customer);

        return "redirect:/";
    }

    @GetMapping(path = "/customers")
    public String showCustomers(Model model) {
        model.addAttribute("customers", customerService.findAllUsers());
        model.addAttribute("customersNumber", customerService.countUsers());

        return "customer-list";
    }

    @GetMapping(path = "/customer/registration")
    public String showAddCustomer(Model model) {
        model.addAttribute("customer", new RegisterRequest());

        return "add-customer";
    }

    @PostMapping(path = "/customer/add")
    public String addCustomer(@ModelAttribute("customer") @Valid RegisterRequest customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-customer";
        }

        customerService.registerCustomer(customer);

        return "redirect:/customers";
    }

    @GetMapping(path = "/customer/delete/{username}")
    public String deleteCustomerById(@PathVariable("username") String username) {
        customerService.deleteUserByUsername(username);

        return "redirect:/customers";
    }

    @PostMapping(path = "/customer/update")
    public String editCustomer(@ModelAttribute("customer") @Valid UserUpdateRequest customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-customer";
        }

        customerService.updateUser(null, customer);

        return "redirect:/customers";
    }

    @GetMapping(path = "/customer/edit/{username}")
    public String showEditPageCustomer(@PathVariable("username") String username, Model model) {
        model.addAttribute("customer", customerService.findUserByUsername(username));

        return "edit-customer";
    }

}
