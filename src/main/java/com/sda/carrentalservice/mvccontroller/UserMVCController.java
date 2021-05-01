package com.sda.carrentalservice.mvccontroller;

import com.sda.carrentalservice.dto.CustomerDTO;
import com.sda.carrentalservice.entity.Branch;
import com.sda.carrentalservice.entity.Customer;
import com.sda.carrentalservice.entity.User;
import com.sda.carrentalservice.service.CustomerService;
import com.sda.carrentalservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserMVCController {

    private final UserService userService;
    private final CustomerService customerService;

    @Autowired
    public UserMVCController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @GetMapping(path = "/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping(path = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "register";
    }

    @PostMapping(path = "/user/register")
    public String registerUser(@ModelAttribute("userRegister") @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
        Optional<User> userOptional = userService.findUserByUsername(customerDTO.getUsername());
        if (userOptional.isPresent()) {
            bindingResult.rejectValue("username", null, "Username already exists!");
        }
        if (!customerDTO.getPassword().equals(customerDTO.getConfirmPassword())) {
            bindingResult.rejectValue("password", null, "Passwords do not match!");
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.registerCustomer(customerDTO);
        return "login";
    }
}
