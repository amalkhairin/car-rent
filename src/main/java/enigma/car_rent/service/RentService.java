package enigma.car_rent.service;

import enigma.car_rent.model.Rent;
import enigma.car_rent.utils.RentDTO;

import java.util.List;

public interface RentService {
    Rent create(RentDTO req);
    List<Rent> getAll();
    Rent getOne(Integer id);

    Rent rentCompleted(Integer id);
}
