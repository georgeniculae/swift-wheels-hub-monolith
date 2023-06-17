package com.carrentalservice.mvccontroller;

import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.service.RevenueService;
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
public class RevenueMvcController {

    private final RevenueService revenueService;

    @GetMapping(path = "/revenues")
    public String showRevenues(Model model) {
        model.addAttribute("revenues", revenueService.findAllRevenues());
        model.addAttribute("revenuesNumber", revenueService.countRevenues());

        return "revenue-list";
    }

    @GetMapping(path = "/revenue/delete/{id}")
    public String deleteRevenueById(@PathVariable("id") Long id) {
        revenueService.deleteRevenueById(id);

        return "redirect:/revenues";
    }

    @GetMapping(path = "/revenue/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("revenue", new RevenueDto());

        return "add-revenue";
    }

    @PostMapping(path = "/revenue/add")
    public String addRevenue(@ModelAttribute("revenue") @Valid RevenueDto revenue, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-revenue";
        }

        revenueService.saveRevenue(revenue);

        return "redirect:/revenues";
    }

    @GetMapping(path = "/revenue/edit/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("revenue", revenueService.findRevenueById(id));

        return "edit-revenue";
    }

    @PostMapping(path = "/revenue/update")
    public String editRevenue(@ModelAttribute("revenue") @Valid RevenueDto revenue, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-revenue";
        }

        revenueService.updateRevenue(revenue);

        return "redirect:/revenues";
    }

}
