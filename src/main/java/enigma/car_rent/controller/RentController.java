package enigma.car_rent.controller;

import enigma.car_rent.model.Rent;
import enigma.car_rent.service.RentService;
import enigma.car_rent.utils.RentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;

    @PostMapping
    public Rent create(@RequestBody RentDTO request) {
        return rentService.create(request);
    }

    @GetMapping("/all")
    public List<Rent> getAll() {
        return rentService.getAll();
    }

    @GetMapping("/{id}")
    public Rent getOne(@PathVariable Integer id) {
        return rentService.getOne(id);
    }


    @PutMapping("/{id}/return")
    public Rent rentCompleted(@PathVariable Integer id) {
        return rentService.rentCompleted(id);
    }

}
