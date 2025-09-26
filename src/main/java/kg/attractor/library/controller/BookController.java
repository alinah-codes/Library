package kg.attractor.library.controller;

import jakarta.validation.Valid;
import kg.attractor.library.dto.BookRequestDto;
import kg.attractor.library.model.Book;
import kg.attractor.library.model.Request;
import kg.attractor.library.model.User;
import kg.attractor.library.repository.RequestRepository;
import kg.attractor.library.service.BookService;
import kg.attractor.library.service.CategoryService;
import kg.attractor.library.service.RequestService;
import kg.attractor.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final RequestService requestService;

    @GetMapping()
    public String getAllBooks(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "4") int size,
                              @RequestParam(defaultValue = "desc") String direction,
                              @RequestParam(required = false) Long categoryId,
                              Model model,
                              Authentication auth) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage;

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("selectedCategoryId", categoryId);

        if (categoryId != null) {
            bookPage = bookService.getBooksByCategoryId(categoryId, pageable);
        } else {
            bookPage = bookService.findAll(pageable);
        }

        model.addAttribute("books", bookPage);
        model.addAttribute("direction", direction);


        if (auth != null && auth.isAuthenticated()) {
            User user = userService.findByPassportNumber(auth.getName());
            Map<String, LocalDate> userRequests = requestService
                    .findActiveRequestsByUser(user)
                    .stream()
                    .collect(Collectors.toMap(
                            r -> String.valueOf(r.getBook().getId()),
                            Request::getReturnDate
                    ));
            model.addAttribute("userRequests", userRequests);
        } else {
            model.addAttribute("userRequests", Collections.emptyMap());
        }
        return "index";
    }



}
