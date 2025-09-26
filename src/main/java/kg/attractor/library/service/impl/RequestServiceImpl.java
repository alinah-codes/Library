package kg.attractor.library.service.impl;

import kg.attractor.library.dto.BookRequestDto;
import kg.attractor.library.model.Book;
import kg.attractor.library.model.LoanStatus;
import kg.attractor.library.model.Request;
import kg.attractor.library.model.User;
import kg.attractor.library.repository.BookRepository;
import kg.attractor.library.repository.RequestRepository;
import kg.attractor.library.repository.UserRepository;
import kg.attractor.library.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public void createRequest(BookRequestDto bookRequestDto){
        User user = userRepository.findByTicketNumber(bookRequestDto.getTicketNumber())
                .orElseThrow(() -> new IllegalArgumentException("Некорректный номер билета"));

        long activeRequests = requestRepository.countByUserIdAndStatus(user.getId(), LoanStatus.ОЖИДАЕТСЯ);

        if (activeRequests >= 3) {
            throw new IllegalStateException("Нельзя взять более трёх книг одновременно");
        }
        boolean exists = requestRepository.existsByBook_IdAndStatus(bookRequestDto.getBookId(), LoanStatus.ОЖИДАЕТСЯ);
        if (exists) {
            throw new IllegalStateException("Книга уже выдана");
        }

        Book book = bookRepository.findById(bookRequestDto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Книга не найдена"));

        Request request = new Request();
        request.setUser(user);
        request.setBook(book);
        request.setCreated(LocalDate.now());
        request.setReturnDate(bookRequestDto.getReturnDate());
        request.setStatus(LoanStatus.ОЖИДАЕТСЯ);

        requestRepository.save(request);
    }

    @Override
    public List<Request> findActiveRequestsByUser(User user) {
        return requestRepository.findByUserAndStatus(user, LoanStatus.ОЖИДАЕТСЯ);
    }

    @Override
    public List<Request> findAllByUser(User user) {
        return requestRepository.findByUser(user);
    }

    @Override
    public void returnBook(User user, Long bookId) {
        Request request = requestRepository
                .findByUserAndBook_IdAndStatus(user, bookId, LoanStatus.ОЖИДАЕТСЯ)
                .orElseThrow(() -> new IllegalArgumentException("Активная заявка не найдена"));

        request.setRealReturnDate(LocalDate.now());
        request.setStatus(LoanStatus.ВОЗВРАЩЕНО);
        requestRepository.save(request);
    }


    @Override
    public List<Request> findRequestsByUserAndDateRange(User user, LocalDate from, LocalDate to) {
        return requestRepository.findByUserAndCreatedBetween(user, from, to);
    }



}
