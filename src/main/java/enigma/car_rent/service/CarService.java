package enigma.car_rent.service;

import enigma.car_rent.model.Car;
import enigma.car_rent.utils.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    Car create(CarDTO req);
    Page<Car> getAll(Pageable pageable);
    Car getOne(Integer id);
    Car update(Integer id, CarDTO req);
    void delete(Integer id);

    void updateAvailable(Integer id, Boolean available);
}
