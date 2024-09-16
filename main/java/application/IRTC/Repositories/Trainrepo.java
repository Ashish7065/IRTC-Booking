package application.IRTC.Repositories;

import application.IRTC.Entity.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface Trainrepo extends JpaRepository<Train, Integer> {

    @Query(value =
            "SELECT t.* FROM Train t " +
                    " WHERE (:startingPoint IS NULL OR t.starting_point ILIKE '%' || :startingPoint || '%')  " +
                    "AND(:destination IS NULL OR t.destination ILIKE '%' || :destination || '%') " +
                    "AND (COALESCE(:dates) IS NULL OR t.date IN (:dates))",
            nativeQuery = true)

    public Page<Train> findBySearch(String startingPoint, String destination, List<LocalDate> dates, Pageable pageable);
}
