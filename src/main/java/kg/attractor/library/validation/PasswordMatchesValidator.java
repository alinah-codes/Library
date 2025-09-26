package kg.attractor.library.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.library.dto.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDto> {
    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        if (userDto.getPassword() == null || userDto.getConfirmPassword() == null) {
            return false;
        }

        boolean valid = userDto.getPassword().equals(userDto.getConfirmPassword());
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Пароли не совпадают")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }
        return valid;
    }
}
