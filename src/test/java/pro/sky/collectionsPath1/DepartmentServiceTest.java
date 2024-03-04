package pro.sky.collectionsPath1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.collectionsPath1.exceptions.EmployeeNotFoundException;
import pro.sky.collectionsPath1.model.Employee;
import pro.sky.collectionsPath1.service.DepartmentService;
import pro.sky.collectionsPath1.service.EmployeeService;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        var employees = List.of(
                new Employee("ivanov1", "ivan1", 3, 2000),
                new Employee("ivanov2", "ivan2", 1, 4000),
                new Employee("ivanov3", "ivan3", 1, 70000),
                new Employee("ivanov4", "ivan4", 2, 33000),
                new Employee("ivanov5", "ivan5", 3, 8000000));
        when(employeeService.getAll()).thenReturn(employees);
    }

    @Test
    void departmentMaxSalaryTest() {
        assertThat(departmentService.getEmployeeWithMaxSalary(1)).isEqualTo(new Employee("ivanov3", "ivan3", 1, 70000));
        assertThat(departmentService.getEmployeeWithMaxSalary(2)).isEqualTo(new Employee("ivanov4", "ivan4", 2, 33000));
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmployeeWithMaxSalary(111));
    }

    @Test
    void departmentMinSalaryTest() {
        assertThat(departmentService.getEmployeeWithMinSalary(1)).isEqualTo(new Employee("ivanov2", "ivan2", 1, 4000));
        assertThat(departmentService.getEmployeeWithMinSalary(2)).isEqualTo(new Employee("ivanov4", "ivan4", 2, 33000));
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmployeeWithMinSalary(111));
    }

    @Test
    void findByDepartmentTest() {
        var actual = departmentService.getEmployeeByDepartment(1);
        assertThat(actual).containsExactlyInAnyOrder(
                new Employee("ivanov2", "ivan2", 1, 4000),
                new Employee("ivanov3", "ivan3", 1, 70000));

        var actual2 = departmentService.getEmployeeByDepartment(13323);
        assertThat(actual2).isEmpty();
    }

    @Test
    void getEmployeeTest() {
        var actual = departmentService.getEmployee();
        var expected = Map.of(
                1, List.of(new Employee("ivanov2", "ivan2", 1, 4000), new Employee("ivanov3", "ivan3", 1, 70000)),
                2, List.of(new Employee("ivanov4", "ivan4", 2, 33000)),
                3, List.of(new Employee("ivanov1", "ivan1", 3, 2000), new Employee("ivanov5", "ivan5", 3, 8000000)));

        assertThat(actual).isEqualTo(expected);
    }
}
