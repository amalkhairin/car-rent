package enigma.car_rent.utils;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RentDTO {
    private Integer user_id;
    private Integer car_id;
    private Boolean completed;
    private Integer price;
    private String started_at;
    private String ends_at;
}
