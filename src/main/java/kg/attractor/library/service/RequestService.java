package kg.attractor.library.service;

import kg.attractor.library.dto.BookRequestDto;
import kg.attractor.library.model.Request;
import kg.attractor.library.model.User;

import java.util.List;

public interface RequestService {
    void createRequest(BookRequestDto bookRequestDto);

    List<Request> findActiveRequestsByUser(User user);
}
