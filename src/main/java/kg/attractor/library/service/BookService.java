package kg.attractor.library.service;

import kg.attractor.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BookService {

    Page<Book> findAll(Pageable pageable);

    Page<Book> getBooksByCategoryId(long categoryID, Pageable pageable);
}
