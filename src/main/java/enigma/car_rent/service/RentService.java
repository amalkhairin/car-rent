package enigma.car_rent.service;

import enigma.car_rent.model.Rent;
import enigma.car_rent.utils.dto.RentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentService {
    Rent create(RentDTO req);
    Page<Rent> getAll(Pageable pageable);
    Rent getOne(Integer id);

    Rent rentCompleted(Integer id);
}
