package enigma.car_rent.controller;

import enigma.car_rent.model.Brand;
import enigma.car_rent.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    public Brand create(@RequestBody Brand request) {
        return brandService.create(request);
    }

    @GetMapping("/all")
    public List<Brand> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public Brand getOne(@PathVariable Integer id) {
        return brandService.getOne(id);
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable Integer id, @RequestBody Brand request) {
        return brandService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        brandService.delete(id);
    }
}
