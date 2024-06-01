package com.swiftwheelshub.mvccontroller;

import com.swiftwheelshub.dto.CustomerRequest;
import com.swiftwheelshub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserMvcController {

    private final UserService userService;

    @GetMapping(path = "/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping(path = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new CustomerRequest());

        return "register";
    }

    @PostMapping(path = "/user/register")
    public String registerUser(@ModelAttribute("customer") @Valid CustomerRequest customerRequest, BindingResult bindingResult) {
        boolean userPresent = userService.existsUserByUsername(customerRequest.getUsername());

        if (userPresent) {
            bindingResult.rejectValue("username", "404", "Username/password incorrect");
        }

        if (!customerRequest.getPassword().equals(customerRequest.getConfirmPassword())) {
            bindingResult.rejectValue("password", "404", "Username/password incorrect");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.registerCustomer(customerRequest);

        return "login";
    }

}
