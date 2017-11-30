package ua.artcode.market.interfaces;

import ua.artcode.market.models.Department;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.money.Money;

import java.time.LocalDateTime;
import java.util.List;

public interface IReport {

    Money doDepartmentReport (List<Employee> employeeList, int i, LocalDateTime start, LocalDateTime end);

    Money calculateSalary(Employee employee, LocalDateTime start,
                          LocalDateTime end);
}
