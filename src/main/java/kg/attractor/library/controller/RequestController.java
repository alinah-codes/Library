package kg.attractor.library.controller;

import jakarta.validation.Valid;
import kg.attractor.library.dto.BookRequestDto;
import kg.attractor.library.model.User;
import kg.attractor.library.service.BookService;
import kg.attractor.library.service.CategoryService;
import kg.attractor.library.service.RequestService;
import kg.attractor.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("request")
@RequiredArgsConstructor
public class RequestController {
    private final BookService bookService;
    private final UserService userService;
    private final RequestService requestService;

    @GetMapping("/{bookId}")
    public String request (@PathVariable Long bookId, Model model, Authentication auth) {
        User user;
        try{
            user =  userService.findByPassportNumber(auth.getName());
        }catch (NullPointerException e){
            return "redirect:/login";
        }

        model.addAttribute("ticketNumber",  user.getTicketNumber());
        model.addAttribute("book", bookService.findById(bookId));
        model.addAttribute("bookRequestDto",  new BookRequestDto());

        return "book/request";
    }

    @PostMapping("/{bookId}")
    public String request(@PathVariable Long bookId,
                          @Valid BookRequestDto bookRequestDto,
                          BindingResult bindingResult,
                          Authentication auth,
                          Model model) {
        User user = userService.findByPassportNumber(auth.getName());
        model.addAttribute("ticketNumber", user.getTicketNumber());
        model.addAttribute("book", bookService.findById(bookId));
        model.addAttribute("bookRequestDto", bookRequestDto);

        if (bindingResult.hasErrors()) {
            return "book/request";
        }

        try {
            requestService.createRequest(bookRequestDto);
            return "redirect:/books";
        } catch (IllegalArgumentException | IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "book/request";
        }
    }

}
