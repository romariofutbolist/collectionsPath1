package pro.sky.collectionsPath1.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException (String message) {
        super(message);
    }
}
