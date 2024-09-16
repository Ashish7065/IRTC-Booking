package application.IRTC.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int seatNumber;
    private LocalDate travelDate;

    @ManyToOne
    @JoinColumn(nullable = false)
   private Train train;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Users users;

}



