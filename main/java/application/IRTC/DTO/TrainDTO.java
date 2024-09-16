package application.IRTC.DTO;

import application.IRTC.Entity.Booking;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class TrainDTO {

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

