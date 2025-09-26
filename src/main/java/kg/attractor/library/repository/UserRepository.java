package kg.attractor.library.repository;

import kg.attractor.library.model.Request;
import kg.attractor.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPassportNumber(String passportNumber);
    boolean existsByPassportNumber(String passportNumber);

    Optional<User> findByTicketNumber(String ticketNumber);


}
