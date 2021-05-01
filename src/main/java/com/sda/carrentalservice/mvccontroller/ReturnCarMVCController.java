package com.sda.carrentalservice.mvccontroller;

import com.sda.carrentalservice.entity.ReturnCar;
import com.sda.carrentalservice.service.EmployeeService;
import com.sda.carrentalservice.service.ReturnCarService;
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
public class ReturnCarMVCController {

    private final ReturnCarService returnCarService;
    private final EmployeeService employeeService;

    @Autowired
    public ReturnCarMVCController(ReturnCarService returnCarService, EmployeeService employeeService) {
        this.returnCarService = returnCarService;
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/returnCars")
    public String showReturnCars(Model model) {
        model.addAttribute("returnCars", this.returnCarService.findAllReturnCar());
        model.addAttribute("returnCarsNumber", this.returnCarService.countReturnCar());
        return "returnCar-list";
    }

    @GetMapping(path = "/returnCar/registration")
    public String showRegistration(Model model, Long id) {
        model.addAttribute("returnCar", new ReturnCar());
//        model.addAttribute("employee", this.employeeService.findEmployeeById(id));
        return "add-returnCar";
    }

    @PostMapping(path = "/returnCar/add")
    public String addReturnCar(@ModelAttribute("returnCar") @Valid ReturnCar returnCar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-returnCar";
        } else {
            this.returnCarService.saveReturnCar(returnCar);
            return "redirect:/returnCars";
        }
    }

    @GetMapping(path = "/returnCar/delete/{id}")
    public String deleteReturnCarById(@PathVariable("id") Long id) {
        this.returnCarService.deleteReturnCarById(id);
        return "redirect:/returnCars";
    }

    @PostMapping(path = "/returnCar/update")
    public String editReturnCar(@ModelAttribute("returnCar") @Valid ReturnCar returnCar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-returnCar";
        }
        this.returnCarService.saveReturnCar(returnCar);
        return "redirect:/returnCars";
    }

    @GetMapping(path = "/returnCar/edit/{id}")
    public String showEditPageReturnCar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("returnCar", this.returnCarService.findReturnCarById(id));
        return "edit-returnCar";
    }
}
