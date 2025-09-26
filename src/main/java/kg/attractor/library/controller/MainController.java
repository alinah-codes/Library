package kg.attractor.library.controller;

import kg.attractor.library.model.Book;
import kg.attractor.library.service.BookService;
import kg.attractor.library.service.CategoryService;
import kg.attractor.library.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class MainController {
    @GetMapping()
    public String index() {
        return "redirect:/books";

    }
}
