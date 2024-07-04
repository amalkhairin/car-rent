package enigma.car_rent.service.implementation;

import enigma.car_rent.model.Role;
import enigma.car_rent.model.UserEntity;
import enigma.car_rent.repository.RoleRepository;
import enigma.car_rent.service.RoleService;
import enigma.car_rent.utils.specification.RoleSpecification;
import enigma.car_rent.utils.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role create(Role req) {
        return roleRepository.save(req);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getOneByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RuntimeException("Role with name " + name + " not found"));
    }

    @Override
    public List<Role> getAllByName(String name) {
        Specification<Role> specification = RoleSpecification.getSpecification(name);
        return roleRepository.findAll(specification);
    }

    @Override
    public Boolean isExistByName(String name) {
        return roleRepository.findByName(name).isPresent();
    }

    @Override
    public Role getOne(Integer id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role with id " + id + " not found"));
    }

    @Override
    public Role update(Integer id, Role req) {
        Role role = this.getOne(id);
        role.setName(req.getName());
        return roleRepository.save(role);
    }

    @Override
    public void delete(Integer id) {
        Role role = this.getOne(id);
        roleRepository.delete(role);
    }
}
