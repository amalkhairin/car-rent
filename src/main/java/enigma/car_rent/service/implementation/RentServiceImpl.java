package enigma.car_rent.service.implementation;

import enigma.car_rent.model.Car;
import enigma.car_rent.model.Rent;
import enigma.car_rent.model.User;
import enigma.car_rent.repository.RentRepository;
import enigma.car_rent.service.CarService;
import enigma.car_rent.service.RentService;
import enigma.car_rent.service.UserService;
import enigma.car_rent.utils.RentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {
    private final RentRepository rentRepository;
    private final UserService userService;
    private final CarService carService;


    @Override
    public Rent create(RentDTO req) {
        Car car = carService.getOne(req.getCar_id());
        User user = userService.getOne(req.getUser_id());
        Rent rent = Rent.builder()
                .car(car)
                .user(user)
                .price(car.getPrice())
                .started_at(LocalDate.parse(req.getStarted_at(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .ends_at(LocalDate.parse(req.getEnds_at(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        car.setAvailable(false);
        carService.updateAvailable(car.getId(), false);

        Integer daysBetween = (int) (ChronoUnit.DAYS.between(rent.getStarted_at(), rent.getEnds_at()));
        rent.setPrice(daysBetween * car.getPrice());
        rent.setCompleted(false);

        user.setBalance(user.getBalance() - rent.getPrice());
        userService.updateBalance(user.getId(), user);
        return rentRepository.save(rent);
    }

    @Override
    public Page<Rent> getAll(Pageable pageable) {
        return rentRepository.findAll(pageable);
    }

    @Override
    public Rent getOne(Integer id) {
        return rentRepository.findById(id).orElse(null);
    }

    @Override
    public Rent rentCompleted(Integer id) {
        Rent rent = this.getOne(id);
        rent.setCompleted(true);
        User user = userService.getOne(rent.getUser().getId());

        if (LocalDate.now().isAfter(rent.getEnds_at())) {
            Integer daysBetween = (int) (ChronoUnit.DAYS.between(rent.getEnds_at(), LocalDate.now()));
            user.setBalance((int) ((user.getBalance() - (rent.getPrice() * 0.1)) * daysBetween));
        }

        userService.updateBalance(user.getId(), user);
        carService.updateAvailable(rent.getCar().getId(), true);
        return rentRepository.save(rent);
    }


}
