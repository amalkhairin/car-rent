package enigma.car_rent.controller;

import enigma.car_rent.model.Rent;
import enigma.car_rent.service.RentService;
import enigma.car_rent.utils.PageResponseWrapper;
import enigma.car_rent.utils.RentDTO;
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
@RequestMapping("/rent")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RentDTO request) {
        return Res.renderJson(
                rentService.create(request),
                "Rent successfully created",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault(size = 10)Pageable pageable
            ) {
        Page<Rent> rents = rentService.getAll(pageable);
        PageResponseWrapper<Rent> result = new PageResponseWrapper<>(rents);
        return Res.renderJson(
                result,
                "success",
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return Res.renderJson(
                rentService.getOne(id),
                "success",
                HttpStatus.OK
        );
    }


    @PutMapping("/{id}/return")
    public ResponseEntity<?> rentCompleted(@PathVariable Integer id) {
        return Res.renderJson(
                rentService.rentCompleted(id),
                "success",
                HttpStatus.OK
        );
    }

}
