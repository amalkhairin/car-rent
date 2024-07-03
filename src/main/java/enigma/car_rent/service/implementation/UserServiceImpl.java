package enigma.car_rent.service.implementation;

import enigma.car_rent.model.User;
import enigma.car_rent.repository.UserRepository;
import enigma.car_rent.service.UserService;
import enigma.car_rent.utils.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(User req) {
        return userRepository.save(req);
    }

    @Override
    public Page<User> getAll(Pageable pageable, String name) {
        Specification<User> specification = UserSpecification.getSpecification(name);
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public User getOne(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public User update(Integer id, User req) {
        User user = this.getOne(id);
        user.setName(req.getName());
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        User user = this.getOne(id);
        userRepository.delete(user);
    }

    @Override
    public User updateBalance(Integer id, User req) {
        User user = this.getOne(id);
        user.setBalance(req.getBalance());
        return userRepository.save(user);
    }

    @Override
    public User topUp(Integer id, User req) {
        User user = this.getOne(id);
        user.setBalance(user.getBalance() + req.getBalance());
        return userRepository.save(user);
    }
}
