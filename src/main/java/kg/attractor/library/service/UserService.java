package kg.attractor.library.service;

import kg.attractor.library.dto.UserDto;
import kg.attractor.library.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    String createUser(UserDto userDto);
}
