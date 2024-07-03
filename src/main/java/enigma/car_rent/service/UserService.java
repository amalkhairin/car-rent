package enigma.car_rent.service;

import enigma.car_rent.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User create(User req);
    Page<User> getAll(Pageable pageable, String name);
    User getOne(Integer id);
    User update(Integer id, User req);
    void delete(Integer id);

    User updateBalance(Integer id, User req);
    User topUp(Integer id, User req);
}
