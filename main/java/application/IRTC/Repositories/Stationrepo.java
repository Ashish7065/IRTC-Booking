package application.IRTC.Repositories;

import application.IRTC.Entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Stationrepo extends JpaRepository<Station, Integer> {
}
