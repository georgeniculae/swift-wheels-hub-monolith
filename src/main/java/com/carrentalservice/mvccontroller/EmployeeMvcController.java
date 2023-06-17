package com.carrentalservice.mvccontroller;

import com.carrentalservice.dto.EmployeeDto;
import com.carrentalservice.service.BranchService;
import com.carrentalservice.service.EmployeeService;
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
public class EmployeeMvcController {

    private final EmployeeService employeeService;
    private final BranchService branchService;

    @GetMapping(path = "/employees")
    public String showEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("employeesNumber", employeeService.countEmployees());

        return "employee-list";
    }

    @GetMapping(path = "/all-employees")
    public String showEmployeesForIndex(Model model) {
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("employeesNumber", employeeService.countEmployees());

        return "index";
    }

    @GetMapping(path = "/employee/delete/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);

        return "redirect:/employees";
    }

    @GetMapping(path = "/employee/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("employee", new EmployeeDto());
        model.addAttribute("allBranches", branchService.findAllBranches());

        return "add-employee";
    }

    @PostMapping(path = "/employee/add")
    public String addEmployee(@ModelAttribute("employee") @Valid EmployeeDto employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-employee";
        }

        employeeService.saveEmployee(employee);

        return "redirect:/employees";

    }

    @GetMapping(path = "/employee/edit/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("employee", employeeService.findEmployeeById(id));
        model.addAttribute("allBranches", branchService.findAllBranches());

        return "edit-employee";
    }

    @PostMapping(path = "/employee/update")
    public String editEmployee(@ModelAttribute("employee") @Valid EmployeeDto employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-employee";
        }

        this.employeeService.updateEmployee(employee);

        return "redirect:/employees";
    }

}
