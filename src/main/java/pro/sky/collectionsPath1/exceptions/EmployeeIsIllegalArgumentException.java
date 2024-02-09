package pro.sky.collectionsPath1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class EmployeeIsIllegalArgumentException extends RuntimeException {
    private final String strings;
    public EmployeeIsIllegalArgumentException(String strings) {
        super("Это значение не подходит: " + strings);
        this.strings = strings;
    }
}
