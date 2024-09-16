package application.IRTC.DTO;



import application.IRTC.Entity.Booking;
import application.IRTC.Entity.Train;
import application.IRTC.Entity.Users;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookingDTO {

    private Long Id;
    private int seatNumber;
    private LocalDate travelDate;
    private Long users;
    private Long train;



}
