package application.IRTC.Service;

import application.IRTC.DTO.BookingDTO;
import application.IRTC.DTO.TrainDTO;
import application.IRTC.Entity.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface Bookingservice {

    public List<Booking> GetAllTicket();

    public BookingDTO CreateTicket(BookingDTO bookingDTO) throws Exception;


    public List<TrainDTO> getBookingDetailsByTrain(Long trainId);
}
