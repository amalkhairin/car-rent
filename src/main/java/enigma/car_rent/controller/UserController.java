package enigma.car_rent.controller;

import enigma.car_rent.model.UserEntity;
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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserEntity request) {
        return Res.renderJson(
                userService.create(request),
                "UserEntity successfully created",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault(size = 10)Pageable pageable,
            @RequestParam(required = false) String name
            ) {
        Page<UserEntity> users = userService.getAll(pageable, name);
        PageResponseWrapper<UserEntity> result = new PageResponseWrapper<>(users);
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
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserEntity request) {
        return Res.renderJson(
                userService.update(id, request),
                "UserEntity successfully updated",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userService.delete(id);
        return Res.renderJson(
                "",
                "UserEntity deleted",
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}/topup")
    public ResponseEntity<?> updateBalance(@PathVariable Integer id, @RequestBody UserEntity req) {
        return Res.renderJson(
                userService.topUp(id, req),
                "Topup successfully",
                HttpStatus.OK
        );
    }

}
