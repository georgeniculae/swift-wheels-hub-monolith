package com.sda.carrentalservice.mvccontroller;

import com.sda.carrentalservice.entity.RentalOffice;
import com.sda.carrentalservice.service.RentalOfficeService;
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
public class RentalOfficeMVCController {

    private final RentalOfficeService rentalOfficeService;

    @Autowired
    public RentalOfficeMVCController(RentalOfficeService rentalOfficeService) {
        this.rentalOfficeService = rentalOfficeService;
    }

    @GetMapping(path = "/rentaloffices")
    public String showBranches(Model model) {
        model.addAttribute("rentalOffices", this.rentalOfficeService.findAllRentalOffices());
        model.addAttribute("rentalOfficesNumber", this.rentalOfficeService.countRentalOffices());
        return "rentaloffice-list";
    }

    @GetMapping(path = "/rentaloffice/delete/{id}")
    public String deleteRentalOfficeById(@PathVariable("id") Long id) {
        rentalOfficeService.deleteRentalOfficeById(id);
        return "redirect:/rentaloffices";
    }

    @PostMapping(path = "/rentaloffice/add")
    public String addRentalOffice(@ModelAttribute("rentaloffice") @Valid RentalOffice rentalOffice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-rentaloffice";
        } else {
            this.rentalOfficeService.saveRentalOffice(rentalOffice);
            return "redirect:/rentaloffices";
        }
    }

    @GetMapping(path = "/rentaloffice/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("rentaloffice", new RentalOffice());
        return "add-rentaloffice";
    }

    @PostMapping(path = "/rentaloffice/update")
    public String editRentalOffice(@ModelAttribute("rentaloffice") @Valid RentalOffice rentalOffice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-rentaloffice";
        } else {
            this.rentalOfficeService.saveRentalOffice(rentalOffice);
            return "redirect:/rentaloffices";
        }
    }

    @GetMapping(path = "/rentaloffice/edit/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rentaloffice", this.rentalOfficeService.findRentalOfficeById(id));
        return "edit-rentaloffice";
    }
}
