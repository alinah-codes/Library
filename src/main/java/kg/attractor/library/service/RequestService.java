package kg.attractor.library.service;

import kg.attractor.library.dto.BookRequestDto;
import kg.attractor.library.model.Request;
import kg.attractor.library.model.User;

import java.time.LocalDate;
import java.util.List;

public interface RequestService {
    void createRequest(BookRequestDto bookRequestDto);

    List<Request> findActiveRequestsByUser(User user);

    List<Request> findAllByUser(User user);

    void returnBook(User user, Long bookId);

    List<Request> findRequestsByUserAndDateRange(User user, LocalDate from, LocalDate to);
}
