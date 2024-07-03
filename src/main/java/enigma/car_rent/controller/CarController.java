package enigma.car_rent.controller;

import enigma.car_rent.model.Car;
import enigma.car_rent.service.CarService;
import enigma.car_rent.utils.CarDTO;
import enigma.car_rent.utils.PageResponseWrapper;
import enigma.car_rent.utils.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CarDTO request) {
        return Res.renderJson(
                carService.create(request),
                "Car successfully created",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault(size = 10)Pageable pageable
            ) {
        Page<Car> cars = carService.getAll(pageable);
        PageResponseWrapper<Car> result = new PageResponseWrapper<>(cars);
        return Res.renderJson(
                result,
                "success",
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return Res.renderJson(
                carService.getOne(id),
                "success",
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody CarDTO request) {
        return Res.renderJson(
                carService.update(id, request),
                "Car successfully updated",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        carService.delete(id);
        return Res.renderJson(
                "",
                "Car deleted",
                HttpStatus.OK
        );
    }
}
