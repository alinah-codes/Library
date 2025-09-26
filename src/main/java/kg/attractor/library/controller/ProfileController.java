package kg.attractor.library.controller;

import kg.attractor.library.model.Request;
import kg.attractor.library.model.User;
import kg.attractor.library.service.RequestService;
import kg.attractor.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final RequestService requestService;

    @GetMapping
    public String profile(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                          Model model,
                          Authentication auth) {
        try {
            User user = userService.findByPassportNumber(auth.getName());
            model.addAttribute("user", user);

            List<Request> requests;
            if (from != null && to != null) {
                System.out.println("release filter");
                requests = requestService.findRequestsByUserAndDateRange(user, from, to);
            } else {
                requests = user.getRequests();
            }
            model.addAttribute("requests", requests);

        } catch (Exception e) {
            return "redirect:/login";
        }
        return "profile/profile";
    }

}
