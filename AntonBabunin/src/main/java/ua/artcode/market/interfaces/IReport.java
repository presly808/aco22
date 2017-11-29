package ua.artcode.market.interfaces;

import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.money.Money;

public interface IReport {

    Money doSalaryOfDepartmentReport(Employee employee, boolean all);
    Money doEmployeeSalaryReport(Employee employee);


}
