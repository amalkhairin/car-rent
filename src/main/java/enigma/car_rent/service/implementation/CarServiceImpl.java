package enigma.car_rent.service.implementation;

import enigma.car_rent.model.Brand;
import enigma.car_rent.model.Car;
import enigma.car_rent.repository.CarRepository;
import enigma.car_rent.service.BrandService;
import enigma.car_rent.service.CarService;
import enigma.car_rent.utils.dto.CarDTO;
import enigma.car_rent.utils.specification.CarSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final BrandService brandService;

    @Override
    public Car create(CarDTO req) {
        Brand brand = brandService.getOne(req.getBrand_id());
        Car car = Car.builder()
                .brand(brand)
                .name(req.getName())
                .price(req.getPrice())
                .available(true)
                .build();
        return carRepository.save(car);
    }

    @Override
    public Page<Car> getAll(Pageable pageable, String name, Boolean available) {
        Specification<Car> specification = CarSpecification.getSpecification(name, available);
        return carRepository.findAll(specification, pageable);
    }

    @Override
    public Car getOne(Integer id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car with id " + id + " not found"));
    }

    @Override
    public Car update(Integer id, CarDTO req) {
        Car car = this.getOne(id);
        car.setBrand(brandService.getOne(req.getBrand_id()));
        car.setName(req.getName());
        car.setPrice(req.getPrice());
        return carRepository.save(car);
    }

    @Override
    public void delete(Integer id) {
        carRepository.deleteById(id);
    }

    @Override
    public void updateAvailable(Integer id, Boolean available) {
        Car car = this.getOne(id);
        car.setAvailable(available);
        carRepository.save(car);
    }
}
