package enigma.car_rent.controller;

import enigma.car_rent.model.User;
import enigma.car_rent.service.UserService;
import enigma.car_rent.utils.PageResponseWrapper;
import enigma.car_rent.utils.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User request) {
        return Res.renderJson(
                userService.create(request),
                "User successfully created",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault(size = 10)Pageable pageable
            ) {
        Page<User> users = userService.getAll(pageable);
        PageResponseWrapper<User> result = new PageResponseWrapper<>(users);
        return Res.renderJson(
                result,
                "success",
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return Res.renderJson(
                userService.getOne(id),
                "success",
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody User request) {
        return Res.renderJson(
                userService.update(id, request),
                "User successfully updated",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userService.delete(id);
        return Res.renderJson(
                "",
                "User deleted",
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}/topup")
    public ResponseEntity<?> updateBalance(@PathVariable Integer id, @RequestBody User req) {
        return Res.renderJson(
                userService.topUp(id, req),
                "Topup successfully",
                HttpStatus.OK
        );
    }

}
