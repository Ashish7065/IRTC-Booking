package application.IRTC.Serviceimpl;

import application.IRTC.DTO.StationDTO;
import application.IRTC.Entity.Station;
import application.IRTC.Repositories.Stationrepo;
import application.IRTC.Service.Stationservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Stationimpl implements Stationservice {

    @Autowired
    private Stationrepo stationrepo;

    @Override
    public List<Station> GetAllStations() {
        List<Station> alltrains = stationrepo.findAll();
        return alltrains;
    }

    @Override
    public StationDTO CreateStations(StationDTO stationDTO ){
        Station station = convertStationDtoToEntity(stationDTO);
        Station stationSave = stationrepo.save(station);
        return ConvertStationEntitytoDto(stationSave);
    }

    private StationDTO ConvertStationEntitytoDto(Station station) {
        StationDTO stationDTO = new StationDTO();
        stationDTO.setId(station.getId());
        stationDTO.setName(station.getName());
        stationDTO.setCode(station.getCode());
        stationDTO.setCity(station.getCity());

        return stationDTO;
    }


    private Station convertStationDtoToEntity(StationDTO stationDTO) {

        Station station = new Station();
        station.setId(stationDTO.getId());
        station.setName(stationDTO.getName());
        station.setCode(stationDTO.getCode());
        station.setCity(stationDTO.getCity());

        return station;

    }
}
