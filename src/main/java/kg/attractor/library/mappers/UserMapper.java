package kg.attractor.library.mappers;

import kg.attractor.library.dto.UserDto;
import kg.attractor.library.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setFullName(user.getFullName());
        dto.setAddress(user.getAddress());
        dto.setPassportNumber(user.getPassportNumber());
        return dto;
    }

    public User toEntity(UserDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setAddress(dto.getAddress());
        user.setPassportNumber(dto.getPassportNumber());
        user.setPassword(dto.getPassword());
        return user;
    }
}
