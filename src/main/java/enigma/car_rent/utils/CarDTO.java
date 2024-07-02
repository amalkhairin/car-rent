package enigma.car_rent.utils;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarDTO {
    private Integer brand_id;
    private String name;
    private Integer price;
    private Boolean available;
}
