package kg.attractor.library.repository;

import kg.attractor.library.model.LoanStatus;
import kg.attractor.library.model.Request;
import kg.attractor.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    long countByUserIdAndStatus(Integer userId, LoanStatus status);

    boolean existsByBook_IdAndStatus(Long bookId, LoanStatus status);

    List<Request> findByUserAndStatus(User user, LoanStatus status);
}
