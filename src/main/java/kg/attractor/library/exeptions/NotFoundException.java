package kg.attractor.library.exeptions;

import java.util.NoSuchElementException;

public class NotFoundException extends NoSuchElementException {
    public NotFoundException(String prefix) {
      super(prefix + " not found");
    }
}
