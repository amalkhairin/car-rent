package enigma.car_rent.utils.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class AuthDTO {
    private String name;
    private String username;
    private String password;
}
