package pro.sky.collectionsPath1.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionsPath1.exceptions.EmployeeNotFoundException;
import pro.sky.collectionsPath1.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployeeWithMaxSalary(Integer department) {
/*
        List<Employee> employees = employeeService.getAll();
        double maxSalary = Double.MIN_VALUE;
        Employee employeeWithMaxSalary = null;

        for (Employee e : employees) {
            if (e.getDepartment() == department && e.getSalary() > maxSalary) {
                maxSalary = e.getSalary();
                employeeWithMaxSalary = e;
            }
        }
        if (employeeWithMaxSalary == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            return employeeWithMaxSalary;
        }
    }
    */
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public Employee getEmployeeWithMinSalary(Integer department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }


    public List<Employee> getEmployeeByDepartment(Integer department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }


    public Map<Integer, List<Employee>> getEmployee() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment(), Collectors.toList()));
    }
}
