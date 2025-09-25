package kg.attractor.library.service.impl;

import kg.attractor.library.model.Book;
import kg.attractor.library.repository.BookRepository;
import kg.attractor.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> getBooksByCategoryId(long categoryID, Pageable pageable) {
        return bookRepository.findByCategory_Id(categoryID, pageable);
    }
}
