package com.swiftwheelshub.mvccontroller;

import com.swiftwheelshub.dto.RentalOfficeRequest;
import com.swiftwheelshub.service.RentalOfficeService;
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
public class RentalOfficeMvcController {

    private final RentalOfficeService rentalOfficeService;

    @GetMapping(path = "/rental-offices")
    public String showBranches(Model model) {
        model.addAttribute("rentalOffices", rentalOfficeService.findAllRentalOffices());
        model.addAttribute("rentalOfficesNumber", rentalOfficeService.countRentalOffices());

        return "rental-office-list";
    }

    @GetMapping(path = "/rental-office/delete/{id}")
    public String deleteRentalOfficeById(@PathVariable("id") Long id) {
        rentalOfficeService.deleteRentalOfficeById(id);

        return "redirect:/rental-offices";
    }

    @PostMapping(path = "/rental-office/add")
    public String addRentalOffice(@ModelAttribute("rentalOffice") @Valid RentalOfficeRequest rentalOffice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-rental-office";
        }

        rentalOfficeService.saveRentalOffice(rentalOffice);

        return "redirect:/rental-offices";
    }

    @GetMapping(path = "/rental-office/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("rentalOffice", new RentalOfficeRequest());

        return "add-rental-office";
    }

    @PostMapping(path = "/rental-office/update")
    public String editRentalOffice(@ModelAttribute("rentalOffice") @Valid RentalOfficeRequest rentalOffice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-rental-office";
        }

        rentalOfficeService.updateRentalOffice(null, rentalOffice);

        return "redirect:/rental-offices";
    }

    @GetMapping(path = "/rental-office/edit/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rentalOffice", rentalOfficeService.findRentalOfficeById(id));

        return "edit-rental-office";
    }

}
