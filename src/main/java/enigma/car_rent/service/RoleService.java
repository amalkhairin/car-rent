package enigma.car_rent.service;

import enigma.car_rent.model.Role;

import java.util.List;

public interface RoleService {
    Role create(Role req);
    List<Role> getAll();
    Role getOneByName(String name);
    List<Role> getAllByName(String name);
    Boolean isExistByName(String name);
    Role getOne(Integer id);
    Role update(Integer id, Role req);
    void delete(Integer id);
}
