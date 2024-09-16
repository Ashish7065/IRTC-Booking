package application.IRTC.Serviceimpl;

import application.IRTC.DTO.BookingDTO;
import application.IRTC.DTO.TrainDTO;
import application.IRTC.Entity.Booking;
import application.IRTC.Entity.Train;
import application.IRTC.Entity.Users;
import application.IRTC.Repositories.Userrepo;
import application.IRTC.Repositories.Bookingrepo;
import application.IRTC.Repositories.Trainrepo;
import application.IRTC.Service.Bookingservice;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Bookingimpl implements Bookingservice {

    @Autowired
    private Bookingrepo bookingrepo;

    @Autowired
    private Trainrepo trainrepo;

    @Autowired
    private Userrepo userrepo;


    @Override
    public List<Booking> GetAllTicket() {
        List<Booking> alltickets = bookingrepo.findAll();
        return alltickets;
    }


    @Override    //2nd
    public BookingDTO CreateTicket(BookingDTO bookingDTO) throws Exception {

        // Fetch train details
        Train trainDetail = trainrepo.findById(Math.toIntExact(bookingDTO.getTrain()))
                .orElseThrow(() -> new EntityNotFoundException("Train not found"));

        // Fetch passenger details
        Users users = userrepo.findById(Math.toIntExact(bookingDTO.getUsers()))
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found"));

        // Calculate available seats
        int totalSeatsBooked = bookingrepo.findTotalSeatsBooked(bookingDTO.getSeatNumber());
        int availableSeats = trainDetail.getSeatingLimit() - totalSeatsBooked;


        // Validate seat availability
        if (bookingDTO.getSeatNumber() >= availableSeats) {
            throw new Exception("Not enough seats available");   // Simpu ,Create a Response to particular message
        } else {
            System.out.println("Seated Successfully");  // Simpu ,Create a Response to particular message
        }


        // Wrap single seat number in a list
        List<Integer> seatNumber = new ArrayList<>();
        seatNumber.add(bookingDTO.getSeatNumber());  // Assuming seatNumber is a single integer

        // Check if any of the requested seats are already booked
        boolean seatsAlreadyBooked = bookingrepo.existsByTrainIdAndSeatNumberIn(trainDetail.getId(), seatNumber);
        if (seatsAlreadyBooked) {
            throw new Exception("One or more selected seats are already booked."); // Simpu ,Create a Response to particular message
        }

        // Convert DTO to entity
        Booking booking = convertBookingDtoToEntity(bookingDTO, trainDetail, users);

        // Save the ticket
        Booking savedBooking = bookingrepo.save(booking);

        // Convert saved entity back to DTO
        return ConvertBookEntitytoDto(savedBooking);
    }



    //3rd

    public List<TrainDTO> getBookingDetailsByTrain(Long trainId) {

        // Fetch the train details to get the seating limit
        Train train = trainrepo.findById(Math.toIntExact(trainId))
                .orElseThrow(() -> new EntityNotFoundException("Train not found"));

        // Fetch booked seats by user
        List<Object[]> bookingData = bookingrepo.findBookedSeatsByUser(trainId);

        // Convert data to DTOs
        List<TrainDTO> bookingDetails = new ArrayList<>();
        for (Object[] data : bookingData) {
            Long userId = (Long) data[0];  // Assuming this is Long
            String userName = (String) data[1];  // Assuming this is String
//            String type = (String) data[2];  // Assuming this is String
//            Integer seatLimit = (Integer) data[3];  // Assuming this is Integer
//            String startingPoint = (String) data[4];  // Assuming this is String
//            String destination = (String) data[5];  // Assuming this is String
//            LocalDate date = (LocalDate) data[6];  // Assuming this is LocalDate
//            Integer nonBookedSeats = (Integer) data[7];  // Assuming this is Integer
//            String number = (String) data[8];  // Assuming this is String



            TrainDTO dto = new TrainDTO();
            dto.setId(userId);  // Assuming TrainDTO.id is a Long
            dto.setName(userName);
//            dto.setType(type);
//            dto.setSeatingLimit(seatLimit);
//            dto.setStartingPoint(startingPoint);
//            dto.setDestination(destination);
//            dto.setDate(date);
//            dto.setNonBookedSeats(nonBookedSeats);
//            dto.setNumber(number);
            bookingDetails.add(dto);
        }



        // Count total booked seats
        int totalBookedSeats = bookingrepo.countTotalBookedSeats(trainId);

        // Calculate non-booked seats
        int nonBookedSeats = train.getSeatingLimit() - totalBookedSeats;

        // Assign the same non-booked seats count for all users
        bookingDetails.forEach(dto -> dto.setNonBookedSeats(nonBookedSeats));

        return bookingDetails;

    }


        private Booking convertBookingDtoToEntity (BookingDTO bookingDTO, Train train, Users user){
            Booking booking = new Booking();
            booking.setId(bookingDTO.getId());
            booking.setSeatNumber(bookingDTO.getSeatNumber());
            booking.setTravelDate(bookingDTO.getTravelDate());
            booking.setTrain(train);
            booking.setUsers(user);

            return booking;
        }

        private BookingDTO ConvertBookEntitytoDto (Booking booking){
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setSeatNumber(booking.getSeatNumber());
            bookingDTO.setTravelDate(booking.getTravelDate());
            bookingDTO.setSeatNumber(booking.getSeatNumber());
            bookingDTO.setTrain(booking.getTrain().getId());
            bookingDTO.setUsers(booking.getUsers().getId());

            return bookingDTO;
        }

    }
