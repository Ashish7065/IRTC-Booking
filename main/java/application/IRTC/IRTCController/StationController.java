package application.IRTC.IRTCController;

import application.IRTC.DTO.StationDTO;
import application.IRTC.Entity.Station;
import application.IRTC.Service.Stationservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private Stationservice stationservice;

    @PostMapping("/create-stations")
    public ResponseEntity<StationDTO> CreateStations(@RequestBody StationDTO stationDTO ) {
        StationDTO stationcreate = stationservice.CreateStations(stationDTO);
        return new ResponseEntity<>(stationcreate, HttpStatus.CREATED);
    }


    @GetMapping("/Get-All-stations")
    public ResponseEntity<List<Station>> GetAllStations() {
        List<Station> allStation = stationservice.GetAllStations();
        return ResponseEntity.ok(allStation);
    }
}
