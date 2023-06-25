package com.carrentalservice.mvccontroller;

import com.carrentalservice.dto.CarDto;
import com.carrentalservice.entity.Car;
import com.carrentalservice.service.BranchService;
import com.carrentalservice.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarMvcController {

    private final CarService carService;
    private final BranchService branchService;

    @GetMapping(path = "/cars")
    public String showCars(Model model, String make) {
        model.addAttribute("cars", carService.findAllCars());
        model.addAttribute("carsNumber", carService.countCars());

        return "car-list";
    }

    @GetMapping({"/search", "/search(make)"})
    public String searchCarByMake(Model model, @RequestParam(value = "make", required = false) String make) {
        List<Car> carList = carService.findCarsByMake(make);

        carList.forEach(car -> model.addAttribute("carByMake", car));

        return "/search";
    }

    @GetMapping(path = "/car/registration")
    public String showRegistration(Model model) {
        model.addAttribute("car", new CarDto());
        model.addAttribute("allBranches", branchService.findAllBranches());

        return "add-car";
    }

    @PostMapping(path = "/car/add")
    public String addCar(@ModelAttribute("car") @Valid CarDto car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-car";
        }

        carService.saveCar(car);

        return "redirect:/cars";
    }

    @GetMapping(path = "/car/delete/{id}")
    public String deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);

        return "redirect:/cars";
    }

    @PostMapping(path = "/car/update")
    public String editCar(@ModelAttribute("car") @Valid CarDto car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-car";
        }

        carService.updateCar(car);

        return "redirect:/cars";
    }

    @GetMapping(path = "/car/edit/{id}")
    public String showEditPageCar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("car", carService.findCarById(id));
        model.addAttribute("allBranches", branchService.findAllBranches());

        return "edit-car";
    }

}
