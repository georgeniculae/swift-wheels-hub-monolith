package com.carrentalservice.mvccontroller;

import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.service.RentalOfficeService;
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
public class RentalOfficeMvcController {

    private final RentalOfficeService rentalOfficeService;

    @Autowired
    public RentalOfficeMvcController(RentalOfficeService rentalOfficeService) {
        this.rentalOfficeService = rentalOfficeService;
    }

    @GetMapping(path = "/rental-offices")
    public String showBranches(Model model) {
        model.addAttribute("rentalOffices", this.rentalOfficeService.findAllRentalOffices());
        model.addAttribute("rentalOfficesNumber", this.rentalOfficeService.countRentalOffices());

        return "rental-office-list";
    }

    @GetMapping(path = "/rental-office/delete/{id}")
    public String deleteRentalOfficeById(@PathVariable("id") Long id) {
        rentalOfficeService.deleteRentalOfficeById(id);

        return "redirect:/rental-offices";
    }

    @PostMapping(path = "/rental-office/add")
    public String addRentalOffice(@ModelAttribute("rental-office") @Valid RentalOffice rentalOffice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-rental-office";
        }

        this.rentalOfficeService.saveRentalOffice(rentalOffice);

        return "redirect:/rental-offices";
    }

    @GetMapping(path = "/rental-office/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("rental-office", new RentalOffice());

        return "add-rental-office";
    }

    @PostMapping(path = "/rental-office/update")
    public String editRentalOffice(@ModelAttribute("rental-office") @Valid RentalOffice rentalOffice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-rental-office";
        }

        this.rentalOfficeService.saveRentalOffice(rentalOffice);

        return "redirect:/rental-offices";
    }

    @GetMapping(path = "/rental-office/edit/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rental-office", this.rentalOfficeService.findRentalOfficeById(id));

        return "edit-rental-office";
    }

}
