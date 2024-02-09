package pro.sky.collectionsPath1.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.collectionsPath1.exceptions.EmployeeAlreadyAddedException;
import pro.sky.collectionsPath1.exceptions.EmployeeIsIllegalArgumentException;
import pro.sky.collectionsPath1.exceptions.EmployeeNotFoundException;
import pro.sky.collectionsPath1.exceptions.EmployeeStorageIsFullException;
import pro.sky.collectionsPath1.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>(List.of());
    public static final int maxEmployees = 20;

    public EmployeeService() {
        employees.add(new Employee("Ivan", "Ivanov", 1, 50000.0));
        employees.add(new Employee("Peter", "Sidorov", 1, 60000.0));
        employees.add(new Employee("Artem", "Petrov", 1, 55000.0));

        employees.add(new Employee("Kirill", "Stepanov", 2, 40000.0));
        employees.add(new Employee("Olya", "Sidorova", 2, 70000.0));

        employees.add(new Employee("Roman", "Artemev", 3, 65000.0));
    }

    private void validateName(String... strings) {
        for (String string : strings) {
            if (!StringUtils.isAlpha(string)) {
                throw new EmployeeIsIllegalArgumentException(string);
            }
        }
    }

    private String validateNames(String strings) {
        return StringUtils.capitalize(strings.toLowerCase());
    }

    public Employee addEmployee(String firstname, String lastname, int department, double salary) {
        validateName(firstname, lastname);

        if (employees.size() > maxEmployees) {
            throw new EmployeeStorageIsFullException("Лимит сотрудников превышен");
        }
        Employee addedEmployee = new Employee(firstname, lastname, department, salary);
//        validateNames(firstname);
//        validateNames(lastname);

        if (employees.contains(addedEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employees.add(addedEmployee);
        return addedEmployee;
    }

    public Employee removeEmployee(String firstname, String lastname, int department, double salary) {
        validateName(firstname, lastname);

        Employee removedEmployee = new Employee(firstname, lastname, department, salary);
        if (!employees.contains(removedEmployee)) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        employees.remove(removedEmployee);
        return removedEmployee;
    }

    public Employee findEmployee(String firstname, String lastname, int department, double salary) {
        validateName(firstname, lastname);

        Employee findedEmployee = new Employee(firstname, lastname, department, salary);
        if (!employees.contains(findedEmployee)) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        Employee result = null;
        for (Employee employee : employees) {
            if (employee.equals(findedEmployee)) {
                return findedEmployee;
            }
        }
        return result;
    }

    public List<Employee> getAll() {
        return employees;
    }
}
