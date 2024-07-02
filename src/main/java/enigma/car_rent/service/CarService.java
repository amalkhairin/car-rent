package enigma.car_rent.service;

import enigma.car_rent.model.Car;
import enigma.car_rent.utils.CarDTO;

import java.util.List;

public interface CarService {
    Car create(CarDTO req);
    List<Car> getAll();
    Car getOne(Integer id);
    Car update(Integer id, CarDTO req);
    void delete(Integer id);

    void updateAvailable(Integer id, Boolean available);
}
