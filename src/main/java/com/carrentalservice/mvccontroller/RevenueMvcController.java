package com.carrentalservice.mvccontroller;

import com.carrentalservice.entity.Revenue;
import com.carrentalservice.service.RevenueService;
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
public class RevenueMvcController {

    private final RevenueService revenueService;

    @Autowired
    public RevenueMvcController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping(path = "/revenues")
    public String showRevenues(Model model) {
        model.addAttribute("revenues", this.revenueService.findAllRevenues());
        model.addAttribute("revenuesNumber", this.revenueService.countRevenues());

        return "revenue-list";
    }

    @GetMapping(path = "/revenue/delete/{id}")
    public String deleteRevenueById(@PathVariable("id") Long id) {
        revenueService.deleteRevenueById(id);

        return "redirect:/revenues";
    }

    @GetMapping(path = "/revenue/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("revenue", new Revenue());

        return "add-revenue";
    }

    @PostMapping(path = "/revenue/add")
    public String addRevenue(@ModelAttribute("revenue") @Valid Revenue revenue, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-revenue";
        }

        this.revenueService.saveRevenue(revenue);

        return "redirect:/revenues";
    }

    @GetMapping(path = "/revenue/edit/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("revenue", this.revenueService.findRevenueById(id));

        return "edit-revenue";
    }

    @PostMapping(path = "/revenue/update")
    public String editRevenue(@ModelAttribute("revenue") @Valid Revenue revenue, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-revenue";
        }

        this.revenueService.saveRevenue(revenue);

        return "redirect:/revenues";
    }

}
