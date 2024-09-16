package application.IRTC.IRTCController;

import application.IRTC.DTO.TrainDTO;

import application.IRTC.Service.Bookingservice;
import application.IRTC.Service.Trainservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    @Autowired
    private Trainservice trainservice;

    @Autowired
    private Bookingservice bookingservice;


    @PostMapping("/create-trains-details")
    public ResponseEntity<TrainDTO> PostTrains(@RequestBody TrainDTO trainDTO ) {
        TrainDTO createdPatient = trainservice.PostTrains(trainDTO );
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }


    @GetMapping("/Get-All-Trains")
    public ResponseEntity<Page<TrainDTO>> GetAllTrains( @RequestParam (required = false) String startingPoint,
                                                        @RequestParam(required = false) String destination,
                                                     @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) List<LocalDate> dates,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        Page<TrainDTO> trainDTOS = trainservice.GetAllTrains(startingPoint, destination ,dates, page, size);
        return ResponseEntity.ok(trainDTOS);
    }

    @GetMapping("/bookings/{trainId}")
    public ResponseEntity<List<TrainDTO>> getBookingDetailsByTrain(@PathVariable Long trainId) {
        List<TrainDTO> bookingDetails = bookingservice.getBookingDetailsByTrain(trainId);
        return ResponseEntity.ok(bookingDetails);
    }

}
