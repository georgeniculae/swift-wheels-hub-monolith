package com.sda.carrentalservice.mvccontroller;

import com.sda.carrentalservice.entity.Car;
import com.sda.carrentalservice.service.BranchService;
import com.sda.carrentalservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CarMVCController {

    private final CarService carService;
    private final BranchService branchService;

    @Autowired
    public CarMVCController(CarService carService, BranchService branchService) {
        this.carService = carService;
        this.branchService = branchService;
    }

    @GetMapping(path = "/cars")
    public String showCars(Model model, String make) {
        model.addAttribute("cars", this.carService.findAllCars());
        model.addAttribute("carsNumber", this.carService.countCars());
        return "car-list";
    }

    @GetMapping({"/search", "/search(make)"})
    public String searchCarByMake(Model model, @RequestParam(value = "make") Optional<String> make) {
        if (make.isPresent()) {
            List<Car> carList = carService.findCarsByMake(make);
            for (Car car1 : carList) {
                model.addAttribute("carByMake", car1);
            }
        }
        return "/search";
    }

    @GetMapping(path = "/car/registration")
    public String showRegistration(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("allBranches", this.branchService.findAllBranches());
        return "add-car";
    }

    @PostMapping(path = "/car/add")
    public String addCar(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-car";
        } else {
            car.setBranch(branchService.findBranchById(car.getBranch().getId()));
            this.carService.saveCar(car);
            return "redirect:/cars";
        }
    }

    @GetMapping(path = "/car/delete/{id}")
    public String deleteCarById(@PathVariable("id") Long id) {
        this.carService.deleteCarById(id);
        return "redirect:/cars";
    }

    @PostMapping(path = "/car/update")
    public String editCar(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-car";
        }
        car.setBranch(branchService.findBranchById(car.getBranch().getId()));
        this.carService.saveCar(car);
        return "redirect:/cars";
    }

    @GetMapping(path = "/car/edit/{id}")
    public String showEditPageCar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("car", this.carService.findCarById(id));
        model.addAttribute("allBranches", this.branchService.findAllBranches());
        return "edit-car";
    }
}
