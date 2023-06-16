package com.carrentalservice.mvccontroller;

import com.carrentalservice.dto.CustomerDto;
import com.carrentalservice.entity.User;
import com.carrentalservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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
        model.addAttribute("customer", new CustomerDto());

        return "register";
    }

    @PostMapping(path = "/user/register")
    public String registerUser(@ModelAttribute("customer") @Valid CustomerDto customerDto, BindingResult bindingResult) {
        Optional<User> userOptional = userService.findUserByUsername(customerDto.getUsername());

        if (userOptional.isPresent()) {
            bindingResult.rejectValue("username", "404", "Username/password incorrect");
        }

        if (!customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
            bindingResult.rejectValue("password", "404", "Username/password incorrect");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.registerCustomer(customerDto);

        return "login";
    }

}
