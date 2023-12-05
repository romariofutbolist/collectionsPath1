package pro.sky.collectionsPath1.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.collectionsPath1.model.Employee;
import pro.sky.collectionsPath1.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @ExceptionHandler(RuntimeException.class)
    public String handlerException(RuntimeException e) {
        return e.getMessage();
    }
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee getPerson(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("department") Integer department, @RequestParam("salary") double salary) {
        return employeeService.addEmployee(firstname, lastname, department, salary);
    }

    @GetMapping("/remove")
    public Employee removePerson(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("department") Integer department, @RequestParam("salary") double salary) {
        return employeeService.removeEmployee(firstname, lastname, department, salary);
    }

    @GetMapping("/find")
    public Employee findPerson(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("department") Integer department, @RequestParam("salary") double salary) {
        return employeeService.findEmployee(firstname, lastname, department, salary);
    }
}

