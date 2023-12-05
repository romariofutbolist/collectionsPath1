package pro.sky.collectionsPath1.model;

import java.util.Objects;
import java.util.Random;

public class Employee {
    private final String firstname;
    private final String lastname;
    private final int department;
    private double salary;


    public Employee(String firstname, String lastname, int department, double salary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.salary = salary;
    }

    public Employee(String firstname, String lastname, Integer department) {
        Random random = new Random();
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = random.nextInt(5) + 1;
        this.salary = random.nextInt(1000) + 1000;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstname.equals(employee.firstname) && lastname.equals(employee.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }
}
