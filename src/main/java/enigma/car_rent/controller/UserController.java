package enigma.car_rent.controller;

import enigma.car_rent.model.User;
import enigma.car_rent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User create(@RequestBody User request) {
        return userService.create(request);
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Integer id) {
        return userService.getOne(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody User request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PutMapping("/{id}/topup")
    public User updateBalance(@PathVariable Integer id, @RequestBody User req) {
        return userService.updateBalance(id, req);
    }
}
