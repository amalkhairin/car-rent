package enigma.car_rent.service.implementation;

import enigma.car_rent.model.Brand;
import enigma.car_rent.model.User;
import enigma.car_rent.repository.BrandRepository;
import enigma.car_rent.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;


    @Override
    public Brand create(Brand req) {
        return brandRepository.save(req);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getOne(Integer id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public Brand update(Integer id, Brand req) {
        Brand brand = this.getOne(id);
        brand.setName(req.getName());
        return brandRepository.save(brand);
    }

    @Override
    public void delete(Integer id) {
        brandRepository.deleteById(id);
    }
}
