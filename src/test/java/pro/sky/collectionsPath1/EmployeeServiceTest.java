package pro.sky.collectionsPath1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.collectionsPath1.exceptions.EmployeeAlreadyAddedException;
import pro.sky.collectionsPath1.exceptions.EmployeeIsIllegalArgumentException;
import pro.sky.collectionsPath1.exceptions.EmployeeNotFoundException;
import pro.sky.collectionsPath1.exceptions.EmployeeStorageIsFullException;
import pro.sky.collectionsPath1.model.Employee;
import pro.sky.collectionsPath1.service.EmployeeService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {

    EmployeeService employeeService = new EmployeeService();

    @Test
    void addEmployeeTest() {
        employeeService.addEmployee("ivanov", "ivan", 2, 10000);
        employeeService.addEmployee("iVANova", "kaTya", 1, 20000);

        Employee actual1 = employeeService.findEmployee("IvAnOv", "IvAN", 2, 10000);
        assertThat(actual1).isNotNull();
        assertThat(actual1.getFirstname()).isEqualTo("Ivanov");
        assertThat(actual1.getLastname()).isEqualTo("Ivan");
        assertThat(actual1.getDepartment()).isEqualTo(2);
        assertThat(actual1.getSalary()).isEqualTo(10000);

        Employee actual2 = employeeService.findEmployee("IVANOVA", "KaTYa", 1, 20000);
        assertThat(actual2).isNotNull();
        assertThat(actual2.getFirstname()).isEqualTo("Ivanova");
        assertThat(actual2.getLastname()).isEqualTo("Katya");
        assertThat(actual2.getDepartment()).isEqualTo(1);
        assertThat(actual2.getSalary()).isEqualTo(20000);
    }

    @Test
    void AddDuplicateTest() {
        employeeService.addEmployee("ivanov", "ivan", 1, 100000);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee("ivanov", "ivan", 1, 100000));
    }

    @Test
    void FullEmployeeTest() {
        employeeService.addEmployee("ivanov", "ivan", 2, 1000);
        employeeService.addEmployee("ivanovv", "ivan", 2, 1000);
        employeeService.addEmployee("ivanovvv", "ivan", 2, 1000);
        employeeService.addEmployee("ivanovvvv", "ivan", 2, 1000);
        employeeService.addEmployee("ivanovvvvv", "ivan", 2, 1000);
        employeeService.addEmployee("ivanovvvvvv", "ivan", 2, 1000);
        employeeService.addEmployee("ivanovvvvvvv", "ivan", 2, 1000);
        employeeService.addEmployee("ivanovvvvvvvv", "ivan", 2, 1000);
        employeeService.addEmployee("ivanovvvvvvvvv", "ivan", 2, 1000);
        employeeService.addEmployee("ivanovvvvvvvvvv", "ivan", 2, 1000);
        employeeService.addEmployee("ivanovvvvvvvvvvv", "ivan", 2, 1000);
        assertThrows(EmployeeStorageIsFullException.class, ()-> employeeService.addEmployee("ivanova","katya", 1, 1000));
    }

    @Test
    void wrongNameTest() {
        assertThrows(EmployeeIsIllegalArgumentException.class,()->employeeService.addEmployee("1ivanov","ivan", 1, 1000));
        assertThrows(EmployeeIsIllegalArgumentException.class,()->employeeService.addEmployee("ivanov34","ivan", 1, 1000));
        assertThrows(EmployeeIsIllegalArgumentException.class,()->employeeService.addEmployee("ivanov","ivan44", 1, 1000));

    }

    @Test
    void getAllTest() {
        employeeService.addEmployee("ivanov", "ivan", 2, 1000);
        employeeService.addEmployee("ivanova", "katya", 1, 2000);

        var actual = employeeService.getAll();
        assertThat(actual).containsExactlyInAnyOrder(
                new Employee("Ivanov", "Ivan", 2, 100),
                new Employee("Ivanova", "Katya", 1, 2000));
    }

    @Test
    void notFoundTest() {
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.findEmployee("ivanova", "masha", 1, 10000));
    }

    @Test
    void removeTest() {
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.removeEmployee("ivanova", "masha", 1, 10000));

        employeeService.addEmployee("ivanova", "yulia", 3, 30000);
        employeeService.addEmployee("ivanov", "roma", 2, 2000);
        var actual = employeeService.findEmployee("ivanova", "yulia", 3, 30000);
        assertThat(actual).isNotNull();
        employeeService.removeEmployee("ivanova", "yulia", 3, 30000);
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.findEmployee("ivanova", "yulia", 3, 30000));
    }

}
