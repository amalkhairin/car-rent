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
    @NotNull
    private String name;
    @NotNull
    private Integer price;
    @NotNull
    private Boolean available;
}
