package pro.sky.collectionsPath1.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionsPath1.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {
        return null;
    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return null;
    }

    @Override
    public Collection<Employee> getEmployee(Integer departmentId) {
        return null;
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeeOfDepartment() {
        return null;
    }
}
