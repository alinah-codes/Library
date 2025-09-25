package kg.attractor.library.controller;

import kg.attractor.library.model.Book;
import kg.attractor.library.service.BookService;
import kg.attractor.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping()
    public String getAllBooks(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "4") int size,
                              @RequestParam(defaultValue = "desc") String direction,
                              @RequestParam(required = false) Long categoryId,
                              Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage;

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("selectedCategoryId", categoryId);

        if (categoryId != null) {
            bookPage = bookService.getBooksByCategoryId(categoryId, pageable);
            model.addAttribute("selectedCategoryId", categoryId);
        } else {
            bookPage = bookService.findAll(pageable);
            model.addAttribute("selectedCategoryId", null);
        }

        model.addAttribute("books", bookPage);
        model.addAttribute("direction", direction);
        return "index";
    }

}
