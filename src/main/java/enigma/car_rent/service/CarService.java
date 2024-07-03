package enigma.car_rent.service;

import enigma.car_rent.model.Car;
import enigma.car_rent.utils.dto.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    Car create(CarDTO req);
    Page<Car> getAll(Pageable pageable, String name, Boolean available);
    Car getOne(Integer id);
    Car update(Integer id, CarDTO req);
    void delete(Integer id);

    void updateAvailable(Integer id, Boolean available);
}
