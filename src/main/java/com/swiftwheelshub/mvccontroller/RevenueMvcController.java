package com.swiftwheelshub.mvccontroller;

import com.swiftwheelshub.service.RevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class RevenueMvcController {

    private final RevenueService revenueService;

    @GetMapping(path = "/revenues")
    public String showRevenues(Model model) {
        model.addAttribute("revenues", revenueService.findAllRevenues());
        model.addAttribute("totalAmount", revenueService.getTotalAmount());

        return "revenue-list";
    }

}
