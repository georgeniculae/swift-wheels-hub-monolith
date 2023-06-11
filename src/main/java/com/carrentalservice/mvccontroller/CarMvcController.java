package com.carrentalservice.mvccontroller;

import com.carrentalservice.entity.Car;
import com.carrentalservice.service.BranchService;
import com.carrentalservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class CarMvcController {

    private final CarService carService;
    private final BranchService branchService;

    @Autowired
    public CarMvcController(CarService carService, BranchService branchService) {
        this.carService = carService;
        this.branchService = branchService;
    }

    @GetMapping(path = "/cars")
    public String showCars(Model model, String make) {
        model.addAttribute("cars", carService.findAllCars());
        model.addAttribute("carsNumber", carService.countCars());

        return "car-list";
    }

    @GetMapping({"/search", "/search(make)"})
    public String searchCarByMake(Model model, @RequestParam(value = "make", required = false) String make) {
        List<Car> carList = carService.findCarsByMake(make);

        for (Car car1 : carList) {
            model.addAttribute("carByMake", car1);
        }

        return "/search";
    }

    @GetMapping(path = "/car/registration")
    public String showRegistration(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("allBranches", branchService.findAllBranches());

        return "add-car";
    }

    @PostMapping(path = "/car/add")
    public String addCar(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-car";
        }

        car.setBranch(branchService.findBranchById(car.getBranch().getId()));
        carService.saveCar(car);

        return "redirect:/cars";
    }

    @GetMapping(path = "/car/delete/{id}")
    public String deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);

        return "redirect:/cars";
    }

    @PostMapping(path = "/car/update")
    public String editCar(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-car";
        }

        car.setBranch(branchService.findBranchById(car.getBranch().getId()));
        carService.saveCar(car);

        return "redirect:/cars";
    }

    @GetMapping(path = "/car/edit/{id}")
    public String showEditPageCar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("car", carService.findCarById(id));
        model.addAttribute("allBranches", branchService.findAllBranches());

        return "edit-car";
    }

}
