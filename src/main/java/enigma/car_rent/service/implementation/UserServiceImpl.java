package enigma.car_rent.service.implementation;

import enigma.car_rent.model.UserEntity;
import enigma.car_rent.repository.UserRepository;
import enigma.car_rent.service.UserService;
import enigma.car_rent.utils.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity req) {
        req.setBalance(0);
        return userRepository.save(req);
    }

    @Override
    public Page<UserEntity> getAll(Pageable pageable, String name) {
        Specification<UserEntity> specification = UserSpecification.getSpecification(name);
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public UserEntity getOneByName(String name) {
        return userRepository.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("UserEntity with name " + name + " not found"));
    }

    @Override
    public Boolean isExistByName(String name) {
        return userRepository.findByUsername(name).isPresent();
    }

    @Override
    public UserEntity getOne(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("UserEntity with id " + id + " not found"));
    }

    @Override
    public UserEntity update(Integer id, UserEntity req) {
        UserEntity userEntity = this.getOne(id);
        userEntity.setName(req.getName());
        return userRepository.save(userEntity);
    }

    @Override
    public void delete(Integer id) {
        UserEntity userEntity = this.getOne(id);
        userRepository.delete(userEntity);
    }

    @Override
    public UserEntity updateBalance(Integer id, UserEntity req) {
        UserEntity userEntity = this.getOne(id);
        userEntity.setBalance(req.getBalance());
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity topUp(Integer id, UserEntity req) {
        UserEntity userEntity = this.getOne(id);
        userEntity.setBalance(userEntity.getBalance() + req.getBalance());
        return userRepository.save(userEntity);
    }
}
