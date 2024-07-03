package enigma.car_rent.utils.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RentDTO {
    @NotNull
    private Integer user_id;
    @NotNull
    private Integer car_id;
    private Boolean completed;
    private Integer price;
    @NotNull
    private String started_at;
    @NotNull
    private String ends_at;
}
