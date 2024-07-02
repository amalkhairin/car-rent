package enigma.car_rent.service;

import enigma.car_rent.model.Brand;

import java.util.List;

public interface BrandService {
    Brand create(Brand req);
    List<Brand> getAll();
    Brand getOne(Integer id);
    Brand update(Integer id, Brand req);
    void delete(Integer id);
}
