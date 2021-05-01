package com.sda.carrentalservice.mvccontroller;

import com.sda.carrentalservice.dto.SearchValueDTO;
import com.sda.carrentalservice.entity.Branch;
import com.sda.carrentalservice.service.BranchService;
import com.sda.carrentalservice.service.CarService;
import com.sda.carrentalservice.service.EmployeeService;
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
public class BranchMVCController {

    private final BranchService branchService;
    private final CarService carService;
    private final EmployeeService employeeService;
    private final RentalOfficeService rentalOfficeService;

    @Autowired
    public BranchMVCController(BranchService branchService, CarService carService, EmployeeService employeeService, RentalOfficeService rentalOfficeService) {
        this.branchService = branchService;
        this.carService = carService;
        this.employeeService = employeeService;
        this.rentalOfficeService = rentalOfficeService;
    }

    @GetMapping(path = "/branches/availableCars")
    public String showAvailableCarsPage(Model model, Long id) {
        model.addAttribute("cars", this.carService.findAllCars());
        return "/availableCars";
    }

    @GetMapping(path = "/branches")
    public String showBranches(Model model) {
        model.addAttribute("branches", this.branchService.findAllBranches());
        model.addAttribute("branchesNumber", this.branchService.countBranches());
        return "branch-list";
    }

    @GetMapping(path = "/allbranches")
    public String showBranchesForIndex(Model model) {
        model.addAttribute("allBranches", this.branchService.findAllBranches());
        model.addAttribute("branchesNumber", this.branchService.countBranches());
        return "index";
    }

    @GetMapping(path = "/branches/branch-id-list")
    public String showBranchById(Model model, Long id) {
        model.addAttribute("allBranches", this.branchService.findAllBranches());
        model.addAttribute("employees", this.employeeService.findAllEmployees());
        model.addAttribute("selectedBranch", "");
        model.addAttribute("search", new SearchValueDTO());
        model.addAttribute("cars", this.carService.findAllCars());
        return "/branch-id-list";
    }

    @GetMapping(path = "/branches/branch-id-list/{id}")
    public String showBranch(@PathVariable("id") Model model, Long id) {
        model.addAttribute("allBranches", this.branchService.findAllBranches());
        model.addAttribute("employees", this.employeeService.findAllEmployees());
        model.addAttribute("selectedBranch", "");
        model.addAttribute("cars", this.carService.findAllCars());
        return "/branch-id-list";
    }

    @GetMapping(path = "/branches/branch-id-list/selected")
    public String showSelectedBranch(Model model, Long id) {
        model.addAttribute("allBranches", this.branchService.findAllBranches());
        model.addAttribute("employees", this.employeeService.findAllEmployees());
        model.addAttribute("selectedBranch", new Branch());
        model.addAttribute("cars", this.carService.findAllCars());
        return "/branch-id-list";
    }

    @GetMapping(path = "/branch/delete/{id}")
    public String deleteBranchById(@PathVariable("id") Long id) {
        branchService.deleteBranchById(id);
        return "redirect:/branches";
    }

    @PostMapping(path = "/branch/add")
    public String addBranch(@ModelAttribute("branch") @Valid Branch branch, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-branch";
        } else {
            branch.setRentalOffice(rentalOfficeService.findRentalOfficeById(branch.getRentalOffice().getId()));
            this.branchService.saveBranch(branch);
            return "redirect:/branches";
        }
    }

    @GetMapping(path = "/branch/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("branch", new Branch());
        model.addAttribute("allRentalOffices", this.rentalOfficeService.findAllRentalOffices());
        return "add-branch";
    }

    @PostMapping(path = "/branch/update")
    public String editBranch(@ModelAttribute("branch") @Valid Branch branch, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-branch";
        } else {
            branch.setRentalOffice(rentalOfficeService.findRentalOfficeById(branch.getRentalOffice().getId()));
            this.branchService.saveBranch(branch);
            return "redirect:/branches";
        }
    }

    @GetMapping(path = "/branch/edit/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("branch", this.branchService.findBranchById(id));
        model.addAttribute("allRentalOffices", this.rentalOfficeService.findAllRentalOffices());
        return "edit-branch";
    }
}
