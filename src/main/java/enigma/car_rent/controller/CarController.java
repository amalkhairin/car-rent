package enigma.car_rent.controller;

import enigma.car_rent.model.Car;
import enigma.car_rent.service.CarService;
import enigma.car_rent.utils.CarDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    public Car create(@RequestBody CarDTO request) {
        return carService.create(request);
    }

    @GetMapping("/all")
    public List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public Car getOne(@PathVariable Integer id) {
        return carService.getOne(id);
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable Integer id,@RequestBody CarDTO request) {
        return carService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        carService.delete(id);
    }
}
