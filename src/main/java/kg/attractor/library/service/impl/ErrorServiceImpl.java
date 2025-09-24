package kg.attractor.library.service.impl;

import kg.attractor.library.exeptions.ErrorResponseBody;
import kg.attractor.library.service.ErrorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public ErrorResponseBody makeResponse(BindingResult bindingResult) {
        Map<String, List<String>> reasons = new HashMap<>();

        bindingResult.getFieldErrors().stream()
                .filter(e -> e.getDefaultMessage() != null)
                .forEach(e -> {
                    List<String> errors = new ArrayList<>();
                    errors.add(e.getDefaultMessage());
                    if (!reasons.containsKey(e.getField())) {
                        reasons.put(e.getField(), errors);
                    }
                });
        return ErrorResponseBody.builder()
                .title("Validation errors")
                .response(reasons)
                .build();
    }

    @Override
    public ErrorResponseBody makeResponse(Exception e) {
        String error = e.getMessage();
        return ErrorResponseBody.builder()
                .title(error)
                .response(Map.of("errors", List.of(error)))
                .build();
    }
}
