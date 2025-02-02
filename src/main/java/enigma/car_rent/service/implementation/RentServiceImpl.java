package enigma.car_rent.service.implementation;

import enigma.car_rent.model.Car;
import enigma.car_rent.model.Rent;
import enigma.car_rent.model.UserEntity;
import enigma.car_rent.repository.RentRepository;
import enigma.car_rent.service.CarService;
import enigma.car_rent.service.RentService;
import enigma.car_rent.service.UserService;
import enigma.car_rent.utils.dto.RentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {
    private final RentRepository rentRepository;
    private final UserService userService;
    private final CarService carService;


    @Override
    public Rent create(RentDTO req) {
        Car car = carService.getOne(req.getCar_id());

        if (!car.getAvailable()) {
            throw new RuntimeException("Car is not available");
        }

        UserEntity userEntity = userService.getOne(req.getUser_id());


        Rent rent = Rent.builder()
                .car(car)
                .userEntity(userEntity)
                .price(car.getPrice())
                .started_at(LocalDate.parse(req.getStarted_at(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .ends_at(LocalDate.parse(req.getEnds_at(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        car.setAvailable(false);

        if (userEntity.getBalance() < rent.getPrice()) {
            throw new RuntimeException("UserEntity does not have enough balance");
        }

        carService.updateAvailable(car.getId(), false);

        Integer daysBetween = (int) (ChronoUnit.DAYS.between(rent.getStarted_at(), rent.getEnds_at()));
        rent.setPrice(daysBetween * car.getPrice());
        rent.setCompleted(false);

        userEntity.setBalance(userEntity.getBalance() - rent.getPrice());
        userService.updateBalance(userEntity.getId(), userEntity);
        return rentRepository.save(rent);
    }

    @Override
    public Page<Rent> getAll(Pageable pageable) {
        return rentRepository.findAll(pageable);
    }

    @Override
    public Rent getOne(Integer id) {
        return rentRepository.findById(id).orElseThrow(() -> new RuntimeException("Rent with id " + id + " not found"));
    }

    @Override
    public Rent rentCompleted(Integer id) {
        Rent rent = this.getOne(id);
        rent.setCompleted(true);
        UserEntity userEntity = userService.getOne(rent.getUserEntity().getId());

        if (LocalDate.now().isAfter(rent.getEnds_at())) {
            Integer daysBetween = (int) (ChronoUnit.DAYS.between(rent.getEnds_at(), LocalDate.now()));
            userEntity.setBalance((int) ((userEntity.getBalance() - (rent.getPrice() * 0.1)) * daysBetween));
        }

        userService.updateBalance(userEntity.getId(), userEntity);
        carService.updateAvailable(rent.getCar().getId(), true);
        return rentRepository.save(rent);
    }


}
