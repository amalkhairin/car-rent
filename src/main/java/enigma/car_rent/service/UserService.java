package enigma.car_rent.service;

import enigma.car_rent.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserEntity create(UserEntity req);
    Page<UserEntity> getAll(Pageable pageable, String name);
    UserEntity getOneByName(String name);
    Boolean isExistByName(String name);
    UserEntity getOne(Integer id);
    UserEntity update(Integer id, UserEntity req);
    void delete(Integer id);

    UserEntity updateBalance(Integer id, UserEntity req);
    UserEntity topUp(Integer id, UserEntity req);
}
