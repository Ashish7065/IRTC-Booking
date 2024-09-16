package application.IRTC.Repositories;

import application.IRTC.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Bookingrepo extends JpaRepository<Booking, Integer> {


    @Query(value = "SELECT COUNT(*) FROM Booking WHERE train_id = :trainId", nativeQuery = true)
    int findTotalSeatsBooked( int trainId);

    @Query(value = "SELECT COUNT(*) > 0 FROM Booking b WHERE b.train_id = :trainId AND b.seat_number IN :seatNumber", nativeQuery = true)
    boolean existsByTrainIdAndSeatNumberIn(Long trainId, List<Integer> seatNumber);



    @Query("SELECT b.users.id AS userId, u.username AS userName, COUNT(b.seatNumber) AS bookedSeats " +
            "FROM Booking b " +
            "JOIN b.users u " +
            "WHERE b.train.id = :trainId " +
            "GROUP BY b.users.id, u.username")
    List<Object[]> findBookedSeatsByUser( Long trainId);

    @Query("SELECT COUNT(b.seatNumber) FROM Booking b WHERE b.train.id = :trainId")
    int countTotalBookedSeats( Long trainId);

}
