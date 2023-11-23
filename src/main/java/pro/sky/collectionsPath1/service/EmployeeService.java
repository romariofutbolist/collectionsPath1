package pro.sky.collectionsPath1.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionsPath1.exceptions.EmployeeAlreadyAddedException;
import pro.sky.collectionsPath1.exceptions.EmployeeNotFoundException;
import pro.sky.collectionsPath1.exceptions.EmployeeStorageIsFullException;
import pro.sky.collectionsPath1.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeService {
    private final HashMap<String, Employee> employees = new HashMap<>();
    public static final int maxEmployees = 3;

    public Employee addEmployee(String firstname, String lastname) {
        if (employees.size() > maxEmployees) {
            throw new EmployeeStorageIsFullException("Лимит сотрудников превышен");
        }

        String key = getKey(firstname, lastname);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }

        Employee addedEmployee = new Employee(firstname, lastname);

        employees.put(key, addedEmployee);
        return addedEmployee;
    }

    public Employee removeEmployee(String firstname, String lastname) {
        Employee removedEmployee = new Employee(firstname, lastname);
        String key = getKey(firstname, lastname);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        employees.remove(key, removedEmployee);
        return removedEmployee;
    }

    public Employee findEmployee(String firstname, String lastname) {
        String key = getKey(firstname, lastname);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return employees.get(key);
    }

    private String getKey(String firstname, String lastname) {
        return firstname + lastname;
    }
}
