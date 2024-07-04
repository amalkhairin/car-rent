package enigma.car_rent.service;

import enigma.car_rent.utils.dto.AuthDTO;
import enigma.car_rent.utils.dto.AuthResponseDTO;
import enigma.car_rent.utils.dto.ResetPasswordDTO;

public interface AuthService {

    AuthResponseDTO login(AuthDTO req);
    void register(AuthDTO req);
}
