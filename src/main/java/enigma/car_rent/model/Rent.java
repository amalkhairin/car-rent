package enigma.car_rent.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean completed;
    private Integer price;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate started_at;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate ends_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
