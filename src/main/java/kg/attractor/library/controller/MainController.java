package kg.attractor.library.controller;

import kg.attractor.library.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping
    public String index(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("authorities", authentication.getAuthorities());
        }
        model.addAllAttributes(mainService.getIndex());
        return "index";
    }
}
