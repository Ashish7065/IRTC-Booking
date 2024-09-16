package application.IRTC.IRTCController;

import application.IRTC.DTO.BookingDTO;
import application.IRTC.Entity.Booking;
import application.IRTC.Service.Bookingservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class BookingController {

    @Autowired
    private Bookingservice bookingservice;

    @PostMapping("/book-ticket")
    public ResponseEntity<BookingDTO> CreateTicket(@RequestBody  BookingDTO bookingDTO) throws Exception {
        BookingDTO Ticketcreate = bookingservice.CreateTicket(bookingDTO);
        return new ResponseEntity<>(Ticketcreate, HttpStatus.CREATED);
    }

    @GetMapping("/Get-All-tickets")
    public ResponseEntity<List<Booking>> GetAllTicket() {
        List<Booking> allticket = bookingservice.GetAllTicket();
        return ResponseEntity.ok(allticket);
    }

//    @GetMapping("/train/{trainId}")
//    public ResponseEntity<TrainDTO> getBookingDetailsByTrain(@PathVariable Long trainId) {
//        TrainDTO bookingDetails = bookingservice.getBookingDetailsByTrain(trainId);
//        return ResponseEntity.ok(bookingDetails);
//    }
}
