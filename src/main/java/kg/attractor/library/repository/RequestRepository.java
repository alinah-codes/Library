package kg.attractor.library.repository;

import kg.attractor.library.model.LoanStatus;
import kg.attractor.library.model.Request;
import kg.attractor.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    long countByUserIdAndStatus(Integer userId, LoanStatus status);

    boolean existsByBook_IdAndStatus(Long bookId, LoanStatus status);

    List<Request> findByUserAndStatus(User user, LoanStatus status);

    List<Request> findByUser(User user);

    Optional<Request> findByUserAndBook_IdAndStatus(User user, Long bookId, LoanStatus status);


    List<Request> findByUserAndCreatedBetween(User user, LocalDate from, LocalDate to);

}
