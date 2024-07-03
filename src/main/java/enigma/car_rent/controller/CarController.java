package enigma.car_rent.controller;

import enigma.car_rent.model.Car;
import enigma.car_rent.service.CarService;
import enigma.car_rent.utils.dto.CarDTO;
import enigma.car_rent.utils.PageResponseWrapper;
import enigma.car_rent.utils.Res;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@Validated
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CarDTO request) {
        return Res.renderJson(
                carService.create(request),
                "Car successfully created",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault(size = 10)Pageable pageable,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean available
            ) {
        Page<Car> cars = carService.getAll(pageable, name, available);
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
