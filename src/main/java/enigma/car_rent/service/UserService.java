package enigma.car_rent.service;

import enigma.car_rent.model.User;

import java.util.List;

public interface UserService {
    User create(User req);
    List<User> getAll();
    User getOne(Integer id);
    User update(Integer id, User req);
    void delete(Integer id);

    User updateBalance(Integer id, User req);
    User topUp(Integer id, User req);
}
