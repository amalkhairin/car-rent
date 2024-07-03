package enigma.car_rent.service;

import enigma.car_rent.model.Rent;
import enigma.car_rent.utils.RentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RentService {
    Rent create(RentDTO req);
    Page<Rent> getAll(Pageable pageable);
    Rent getOne(Integer id);

    Rent rentCompleted(Integer id);
}
