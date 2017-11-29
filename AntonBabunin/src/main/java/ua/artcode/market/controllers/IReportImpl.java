package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IReport;
import ua.artcode.market.interfaces.ITerminalController;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.HeadOfSalesmen;
import ua.artcode.market.models.money.Money;

import java.io.IOException;
import java.util.List;

public class IReportImpl implements IReport {

//    ITerminalController terminalController;
//
//    public IReportImpl() throws IOException {
//        this.terminalController = TerminalControllerFactory.create();
//    }


    //true - all subordinated employee, false - only himself
    @Override
    public Money doSalaryReport(Employee employee, boolean all) {
        if (all == true) {
            if (employee instanceof HeadOfSalesmen) {
                List<Employee> employeeList =
                        employee.getSubordinateList();
                return employee.getSalary().doSum(doSubordinateReport(0, employeeList));
            }
        }
        return employee.getSalary();
    }

    private Money doSubordinateReport(int i, List<Employee> employeeList) {
        Money summ = new Money(0,0);
        if (employeeList == null || employeeList.isEmpty() ||
                i >= employeeList.size()) return summ;

        return employeeList.get(i).getSalary().doSum(doSubordinateReport(i+1, employeeList));
    }

    @Override
    public Money doSalesReport(Employee employee, boolean all) {
        return null;
    }
}
