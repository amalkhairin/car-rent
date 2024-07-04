package enigma.car_rent.service.implementation;

import enigma.car_rent.model.UserEntity;
import enigma.car_rent.repository.UserRepository;
import enigma.car_rent.security.JWTGenerator;
import enigma.car_rent.service.AuthService;
import enigma.car_rent.service.RoleService;
import enigma.car_rent.service.UserService;
import enigma.car_rent.utils.dto.AuthDTO;
import enigma.car_rent.utils.dto.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthResponseDTO login(AuthDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        System.out.println("dslkdasdhkjsdhakjdhsjkdhak");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new AuthResponseDTO(token);
    }

    @Override
    public void register(AuthDTO request) {
        if (userService.isExistByName(request.getUsername())) {
            throw new RuntimeException("User with username " + request.getUsername() + " already exists");
        }

        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        user.setRoles(roleService.getAllByName("USER"));
        userService.create(user);
    }
}
