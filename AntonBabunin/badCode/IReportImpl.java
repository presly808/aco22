package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.interfaces.IReport;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.HeadOfSalesmen;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.models.money.Money;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        if (all) {
            if (employee instanceof HeadOfSalesmen) {
                List<Employee> employeeList =
                        employee.getSubordinateList();
                return employee.getSalary().doSum(doSubordinateReport(0, employeeList));
            }
        }
        return employee.getSalary();
    }

    @Override
    public Money doEmployeeSalaryReport(Employee employee, LocalDateTime start, LocalDateTime end) {
        List<Employee> employeeList = employee.getSubordinateList();
        if (employee instanceof HeadOfSalesmen) {
            Money billsSubordinateSumm = billsSumm(employeeList, 0, 0);
            Money sellerSalesPercent = billsSubordinateSumm.takePercent(2);
            Money employeePercent = employee.getSalary().doSum(salesPercent(employee));
            return sellerSalesPercent.doSum(employeePercent);
        }

        return employee.getSalary().doSum(salesPercent(employee));
    }

    private Money billsSumm(List<Employee> employeeList, int i, int i1) {
    }


    private Money salesPercent(Employee employee) {
        List<Bill> bills = iAppDb.filter(employee, null, null, null, null);
        return billsSumm(bills, 0).takePercent(employee.getPercent());
    }

//        получить список билов от всех подчиненных продавцов и вернуть % от суммы


    private List<Bill> getBillsSellers(Employee employee, int i, LocalDateTime start, LocalDateTime end) {
        List<Bill> bills = new ArrayList<>();
        List<Employee> employeeList = employee.getSubordinateList();
        if (employeeList == null || employeeList.isEmpty() || i >= employeeList.size()) return bills;

        Employee employee1 = getSubEmployee(employeeList.get(0), i+1);
        bills.addAll(iAppDb.filter(employee1, null, start, end,null));
        return bills;
    }

    private Employee getSubEmployee(Employee employee, int i) {
        Employee employee1 = new HeadOfSalesmen("0", "0", "0", new Money(0,0));

        if (employee.getSubordinateList() == null || employee.getSubordinateList().isEmpty() || i >=employee.getSubordinateList().size()) return employee1;

        return getSubEmployee(employee.getSubordinateList().get(i+1), i+1);

    }


    private Money doSubordinateReport(int i, List<Employee> employeeList) {
        Money summ = new Money(0,0);
        if (employeeList == null || employeeList.isEmpty() ||
                i >= employeeList.size()) return summ;

        return employeeList.get(i).getSalary().doSum(doSubordinateReport(i+1, employeeList));
    }


    private Money billsSumm(List<Bill> bills, int i) {
        Money summ = new Money(0,0);
        if (bills == null || bills.isEmpty() || i >= bills.size()) return summ;

        return bills.get(i).getAmountPrice().doSum(billsSumm(bills, i + 1));
    }
}
