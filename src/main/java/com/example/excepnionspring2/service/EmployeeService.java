package com.example.excepnionspring2.service;

import com.example.excepnionspring2.exception.EmployeeAlreadyAddedException;
import com.example.excepnionspring2.exception.EmployeeNotFoundException;
import com.example.excepnionspring2.exception.EmployeeStoragelsFullException;
import com.example.excepnionspring2.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private static final int MAX_SIZE = 5; // константа мах колл чел.

    public Employee add(String firstName, String lastName) { // добавим сотр.
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStoragelsFullException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName);
        if (employees.contains(employeeToAdd)) {
            throw new EmployeeAlreadyAddedException();
        }

        employees.add(employeeToAdd);
        return employeeToAdd; // вернем сотрудника который пришел.
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.contains(employeeToRemove)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employeeToRemove);
        return employeeToRemove; // вернем сотрудника который пришел.
    }

    public Employee find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (firstName.equalsIgnoreCase(employee.getFirstName()) && lastName.equalsIgnoreCase
                    (employee.getLastName())) {
                //если имя и фамилия совпадают то вернем текущий объект
                return employee;
            }
        }
        throw new EmployeeNotFoundException();

    }

    public List<Employee> getAll() {
        return Collections.unmodifiableList(employees);
    }
}







