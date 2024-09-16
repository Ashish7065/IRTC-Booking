package application.IRTC.Service;


import application.IRTC.DTO.TrainDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface Trainservice {

    public Page<TrainDTO> GetAllTrains(String startingPoint, String destination, List<LocalDate> dates, int page, int size);

    public TrainDTO PostTrains(TrainDTO trainDTO );


}
