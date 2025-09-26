package kg.attractor.library.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kg.attractor.library.dto.UserDto;
import kg.attractor.library.model.User;
import kg.attractor.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Неверный логин или пароль");
        }
        return "auth/login";
    }

    @GetMapping("auth/register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/register";
    }

    @PostMapping("auth/register")
    public String register(@Valid @ModelAttribute("userDto") UserDto userDto,
                           BindingResult bindingResult,
                           Model model,
                           HttpServletRequest request,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "auth/register";
        }

        String ticket;
        try {
             ticket = userService.createUser(userDto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage" , e.getMessage());
            return "auth/register";
        }


        UserDetails userDetails = userService.loadUserByUsername(userDto.getPassportNumber());
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authToken);

        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        redirectAttributes.addFlashAttribute("successMessage",
                "Билет успешно создан. Номер билета: " + ticket);

        return "redirect:/books";
    }

}
