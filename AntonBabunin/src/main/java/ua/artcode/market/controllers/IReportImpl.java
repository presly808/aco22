package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.interfaces.IReport;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.HeadOfSalesmen;
import ua.artcode.market.models.money.Money;

import java.util.List;

public class IReportImpl implements IReport {

    private IAppDb iAppDb;

    public IReportImpl(IAppDb iAppDb) {
        this.iAppDb = iAppDb;
    }
    public IAppDb getiAppDb() {
        return iAppDb;
    }


    //true - all subordinated employee, false - only himself
    @Override
    public Money doSalaryOfDepartmentReport(Employee employee, boolean all) {
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
    public Money doEmployeeSalaryReport(Employee employee) {
        if (employee instanceof HeadOfSalesmen) {
            Money sellerSalesPercent = sellerSalesPercent(employee);
        }

        return employee.getSalary().doSum(salesPercent(employee));
    }

    private Money sellerSalesPercent(Employee employee, int i) {
        //получить список билов от всех подчиненных продавцов и вернуть % от суммы


        takeBillsA
        List subordinateList = employee.getSubordinateList();
        if (subordinateList == null || subordinateList.isEmpty() || i >= subordinateList.size()) return null;

        return subordinateList.get(i)
    }

    private Money salesPercent(Employee employee) {
        List<Bill> bills = iAppDb.filter(employee, null, null, null, null);
        return billsSumm(bills, 0).takePercent(employee.getPercent());
    }

    private Money billsSumm(List<Bill> bills, int i) {
        Money summ = new Money(0,0);
        if (bills == null || bills.isEmpty() || i >= bills.size()) return summ;

        return bills.get(i).getAmountPrice().doSum(billsSumm(bills, i + 1));
    }
}
