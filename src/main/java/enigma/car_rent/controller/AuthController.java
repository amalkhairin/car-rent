package enigma.car_rent.controller;

import enigma.car_rent.security.JWTGenerator;
import enigma.car_rent.service.AuthService;
import enigma.car_rent.service.UserService;
import enigma.car_rent.utils.Res;
import enigma.car_rent.utils.dto.AuthDTO;
import enigma.car_rent.utils.dto.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO request) {
        return Res.renderJson(
                authService.login(request),
                "Login successful",
                HttpStatus.OK
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthDTO request) {
        authService.register(request);
        return Res.renderJson(
                null,
                "User successfully registered",
                HttpStatus.CREATED
        );
    }
}
