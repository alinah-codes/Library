package kg.attractor.library.service.impl;

import kg.attractor.library.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    @Override
    public Map<String, Object> getIndex() {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Hello world!!!");
        return result;
    }
}
