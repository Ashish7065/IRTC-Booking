package application.IRTC.Service;

import application.IRTC.DTO.StationDTO;
import application.IRTC.DTO.TrainDTO;
import application.IRTC.Entity.Station;
import application.IRTC.Entity.Train;

import java.util.List;

public interface Stationservice {

    public List<Station> GetAllStations();

    public StationDTO CreateStations(StationDTO stationDTO );

}
