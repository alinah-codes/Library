package kg.attractor.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "ФИО обязательно")
    @Size(min = 5, max = 100, message = "ФИО должно быть от 5 до 100 символов")
    private String fullName;

    @NotBlank(message = "Адрес обязателен")
    @Size(min = 5, max = 200, message = "Адрес должен быть от 5 до 200 символов")
    private String address;

    @NotBlank(message = "Номер паспорта обязателен")
    @Pattern(regexp = "^[A-Z0-9]{6,12}$", message = "Номер паспорта должен содержать 6–12 символов: только латиница и цифры")
    private String passportNumber;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    private String password;
}
