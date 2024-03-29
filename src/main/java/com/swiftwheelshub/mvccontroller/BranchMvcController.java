package com.swiftwheelshub.mvccontroller;

import com.swiftwheelshub.dto.BranchRequest;
import com.swiftwheelshub.dto.SearchValue;
import com.swiftwheelshub.service.BranchService;
import com.swiftwheelshub.service.CarService;
import com.swiftwheelshub.service.EmployeeService;
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
public class BranchMvcController {

    private final BranchService branchService;
    private final CarService carService;
    private final EmployeeService employeeService;
    private final RentalOfficeService rentalOfficeService;

    @GetMapping(path = "/branches/available-cars")
    public String showAvailableCarsPage(Model model) {
        model.addAttribute("cars", carService.findAllCars());

        return "/availableCars";
    }

    @GetMapping(path = "/branches")
    public String showBranches(Model model) {
        model.addAttribute("branches", branchService.findAllBranches());
        model.addAttribute("branchesNumber", branchService.countBranches());

        return "branch-list";
    }

    @GetMapping(path = "/all-branches")
    public String showBranchesForIndex(Model model) {
        model.addAttribute("allBranches", branchService.findAllBranches());
        model.addAttribute("branchesNumber", branchService.countBranches());

        return "index";
    }

    @GetMapping(path = "/branches/branch-id-list")
    public String showBranchById(Model model) {
        model.addAttribute("allBranches", branchService.findAllBranches());
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("selectedBranch", "");
        model.addAttribute("search", new SearchValue());
        model.addAttribute("cars", carService.findAllCars());

        return "/branch-id-list";
    }

    @GetMapping(path = "/branches/branch-id-list/{id}")
    public String showBranch(@PathVariable("id") Model model, Long id) {
        model.addAttribute("allBranches", branchService.findAllBranches());
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("selectedBranch", "");
        model.addAttribute("cars", carService.findAllCars());

        return "/branch-id-list";
    }

    @GetMapping(path = "/branches/branch-id-list/selected")
    public String showSelectedBranch(Model model) {
        model.addAttribute("allBranches", branchService.findAllBranches());
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("selectedBranch", new BranchRequest());
        model.addAttribute("cars", carService.findAllCars());

        return "/branch-id-list";
    }

    @GetMapping(path = "/branch/delete/{id}")
    public String deleteBranchById(@PathVariable("id") Long id) {
        branchService.deleteBranchById(id);

        return "redirect:/branches";
    }

    @PostMapping(path = "/branch/add")
    public String addBranch(@ModelAttribute("branch") @Valid BranchRequest branch, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-branch";
        }

        branchService.saveBranch(branch);

        return "redirect:/branches";
    }

    @GetMapping(path = "/branch/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("branch", new BranchRequest());
        model.addAttribute("allRentalOffices", rentalOfficeService.findAllRentalOffices());

        return "add-branch";
    }

    @PostMapping(path = "/branch/update")
    public String editBranch(@ModelAttribute("branch") @Valid BranchRequest branch, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-branch";
        }

        branchService.updateBranch(null, branch);

        return "redirect:/branches";
    }

    @GetMapping(path = "/branch/edit/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("branch", branchService.findBranchById(id));
        model.addAttribute("allRentalOffices", rentalOfficeService.findAllRentalOffices());

        return "edit-branch";
    }

}
