package kg.attractor.library.repository;

import kg.attractor.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPassportNumber(String passportNumber);
    boolean existsByPassportNumber(String passportNumber);

}
