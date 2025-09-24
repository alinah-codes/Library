package kg.attractor.library.service;
import kg.attractor.library.exeptions.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(BindingResult bindingResult);

    ErrorResponseBody makeResponse(Exception e);
}
