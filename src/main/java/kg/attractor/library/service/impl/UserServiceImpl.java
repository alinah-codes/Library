package kg.attractor.library.service.impl;
import kg.attractor.library.dto.UserDto;
import kg.attractor.library.exeptions.UserNotFoundException;
import kg.attractor.library.model.User;
import kg.attractor.library.repository.RoleRepository;
import kg.attractor.library.repository.UserRepository;
import kg.attractor.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private static final Random random = new Random();


    @Override
    public String createUser(UserDto userDto) {
        User user = mapToUser(userDto, encoder, roleRepository, userRepository);
        return userRepository.saveAndFlush(user).getTicketNumber();
    }

    public static User mapToUser(UserDto userDto, PasswordEncoder encoder, RoleRepository roleRepository, UserRepository userRepository) {
        if (userRepository.existsByPassportNumber(userDto.getPassportNumber())) {
            throw new IllegalArgumentException("Пользователь с таким паспортным номером уже существует");
        }
        User newUser = new User();
        newUser.setFullName(userDto.getFullName());
        newUser.setAddress(userDto.getAddress());
        newUser.setPassportNumber(userDto.getPassportNumber());
        newUser.setTicketNumber(generateTicketNumber());
        newUser.setPassword(encoder.encode(userDto.getPassword()));
        newUser.setRole(roleRepository.findByRoleName("READER"));
        newUser.setEnabled(true);
        return newUser;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByPassportNumber(login)
                .orElseThrow(() -> new UsernameNotFoundException(login));


        return new org.springframework.security.core.userdetails.User(
                user.getPassportNumber(),
                user.getPassword(),
                List.of()
        );
    }


    private static String generateTicketNumber() {
        int number = 10000 + random.nextInt(90000);
        return "TICKET-" + number;
    }

    @Override
    public User findByPassportNumber(String passportNumber) {
        return userRepository.findByPassportNumber(passportNumber)
                .orElseThrow(UserNotFoundException::new);
    }
}
