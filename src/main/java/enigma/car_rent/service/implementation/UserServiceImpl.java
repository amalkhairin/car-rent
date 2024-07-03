package enigma.car_rent.service.implementation;

import enigma.car_rent.model.User;
import enigma.car_rent.repository.UserRepository;
import enigma.car_rent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getOne(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User update(Integer id, User req) {
        User user = this.getOne(id);
        user.setName(req.getName());
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
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
