package application.IRTC.Repositories;

import application.IRTC.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepo extends JpaRepository<Users, Integer> {
}
