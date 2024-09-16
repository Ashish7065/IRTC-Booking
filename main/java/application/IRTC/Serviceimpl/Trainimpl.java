package application.IRTC.Serviceimpl;

import application.IRTC.DTO.TrainDTO;
import application.IRTC.Entity.Train;
import application.IRTC.Repositories.Trainrepo;
import application.IRTC.Service.Trainservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Trainimpl implements Trainservice {

    @Autowired
    private Trainrepo trainrepo;


    @Override
    public TrainDTO PostTrains(TrainDTO trainDTO) {
        Train train = convertTrainDtoToEntity(trainDTO);
        Train TrainSave = trainrepo.save(train);
        return ConvertTrainEntitytoDto(TrainSave);
    }

    //Native query (for getall Details in Pagination form)
    public Page<TrainDTO> GetAllTrains(String startingPoint, String destination, List<LocalDate> dates, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Train> result;

        if (startingPoint == null && destination == null) {
            result = trainrepo.findAll(pageable);
        }

        List<LocalDate> filteredDates = dates != null ? dates : Collections.emptyList();

        result = trainrepo.findBySearch(startingPoint, destination, filteredDates, pageable);


        List<TrainDTO> trainList = result.getContent().stream()
                .map(train -> new TrainDTO(
                        train.getId(),
                        train.getName(),
                        train.getNumber(),
                        train.getType(),
                        train.getSeatingLimit(),
                        train.getStartingPoint(),
                        train.getDestination(),
                        train.getDate(),
                        train.getNonBookedSeats()


                ))
                .collect(Collectors.toList());

        return new PageImpl<>(trainList, pageable, result.getTotalElements());


    }

    private TrainDTO ConvertTrainEntitytoDto(Train train) {
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setId(train.getId());
        trainDTO.setName(train.getName());
        trainDTO.setType(train.getType());
        trainDTO.setSeatingLimit(train.getSeatingLimit());
        trainDTO.setNumber(train.getNumber());
        trainDTO.setStartingPoint(train.getStartingPoint());
        trainDTO.setDestination(train.getDestination());
        trainDTO.setDate(train.getDate());
        trainDTO.setNonBookedSeats(train.getNonBookedSeats());



        return trainDTO;
    }


    private Train convertTrainDtoToEntity(TrainDTO trainDTO) {

        Train train = new Train();
        train.setId(trainDTO.getId());
        train.setName(trainDTO.getName());
        train.setNumber(trainDTO.getNumber());
        train.setType(trainDTO.getType());
        train.setSeatingLimit(trainDTO.getSeatingLimit());
        train.setStartingPoint(trainDTO.getStartingPoint());
        train.setDestination(trainDTO.getDestination());
        train.setDate(trainDTO.getDate());
        train.setNonBookedSeats(trainDTO.getNonBookedSeats());


        return train;

    }
}
