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
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;
    private String type; // Express, Superfast, etc.
    private int seatingLimit;
    private String startingPoint;
    private String destination;
    private LocalDate date;
    private int nonBookedSeats;


//    private int bookedSeats;

   


}
