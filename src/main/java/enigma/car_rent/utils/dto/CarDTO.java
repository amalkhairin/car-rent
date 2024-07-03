package enigma.car_rent.utils.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarDTO {
    @NotNull
    private Integer brand_id;
    @NotBlank
    private String name;
    @NotNull
    private Integer price;
    private Boolean available;
}
