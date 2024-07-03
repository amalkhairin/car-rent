package enigma.car_rent.controller;

import enigma.car_rent.model.Brand;
import enigma.car_rent.service.BrandService;
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
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Brand request) {
        return Res.renderJson(
                brandService.create(request),
                "Brand successfully created",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault(size = 10)Pageable pageable
            ) {
        Page<Brand> brands = brandService.getAll(pageable);
        PageResponseWrapper<Brand> result = new PageResponseWrapper<>(brands);
        return Res.renderJson(
                result,
                "success",
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return Res.renderJson(
                brandService.getOne(id),
                "success",
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Brand request) {
        return Res.renderJson(
                brandService.update(id, request),
                "Brand successfully updated",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        brandService.delete(id);
        return Res.renderJson(
                "",
                "Brand deleted",
                HttpStatus.OK
        );
    }
}
