package pro.sky.collectionsPath1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collectionsPath1.model.Employee;
import pro.sky.collectionsPath1.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam("department") Integer department) {
        return departmentService.getEmployeeWithMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam("department") Integer department) {
        return departmentService.getEmployeeWithMinSalary(department);
    }

    @GetMapping("/all")
    public List<Employee> getEmployeeByDepartment(@RequestParam("department") Integer department) {
        return departmentService.getEmployeeByDepartment(department);
    }

    @GetMapping
    public Map<Integer, List<Employee>> getEmployee() {
        return departmentService.getEmployee();
    }
}
