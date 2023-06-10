package com.carrentalservice.mvccontroller;

import com.carrentalservice.entity.ReturnCar;
import com.carrentalservice.service.ReturnCarService;
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
public class ReturnCarMvcController {

    private final ReturnCarService returnCarService;

    @Autowired
    public ReturnCarMvcController(ReturnCarService returnCarService) {
        this.returnCarService = returnCarService;
    }

    @GetMapping(path = "/return-cars")
    public String showReturnCars(Model model) {
        model.addAttribute("returnCars", this.returnCarService.findAllReturnCar());
        model.addAttribute("returnCarsNumber", this.returnCarService.countReturnCar());

        return "returnCar-list";
    }

    @GetMapping(path = "/return-car/registration")
    public String showRegistration(Model model, Long id) {
        model.addAttribute("returnCar", new ReturnCar());
//        model.addAttribute("employee", this.employeeService.findEmployeeById(id));

        return "add-returnCar";
    }

    @PostMapping(path = "/return-car/add")
    public String addReturnCar(@ModelAttribute("returnCar") @Valid ReturnCar returnCar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-returnCar";
        }

        this.returnCarService.saveReturnCar(returnCar);

        return "redirect:/returnCars";
    }

    @GetMapping(path = "/returnCar/delete/{id}")
    public String deleteReturnCarById(@PathVariable("id") Long id) {
        this.returnCarService.deleteReturnCarById(id);

        return "redirect:/returnCars";
    }

    @PostMapping(path = "/return-car/update")
    public String editReturnCar(@ModelAttribute("returnCar") @Valid ReturnCar returnCar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-returnCar";
        }

        this.returnCarService.saveReturnCar(returnCar);

        return "redirect:/return-cars";
    }

    @GetMapping(path = "/return-car/edit/{id}")
    public String showEditPageReturnCar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("returnCar", this.returnCarService.findReturnCarById(id));

        return "edit-returnCar";
    }
}
