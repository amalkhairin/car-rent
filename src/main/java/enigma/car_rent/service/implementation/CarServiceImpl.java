package enigma.car_rent.service.implementation;

import enigma.car_rent.model.Brand;
import enigma.car_rent.model.Car;
import enigma.car_rent.repository.CarRepository;
import enigma.car_rent.service.BrandService;
import enigma.car_rent.service.CarService;
import enigma.car_rent.utils.CarDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<Car> getAll(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public Car getOne(Integer id) {
        return carRepository.findById(id).orElse(null);
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
