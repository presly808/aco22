package ua.artcode.market.interfaces;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.IReportImpl;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.HeadOfSalesmen;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.models.money.Money;
//import ua.artcode.market.models.money.Salary;
import ua.artcode.market.utils.Generator;

import java.util.ArrayList;
import java.util.List;

public class IReportTest {

    private IReport iReport;

    @Before
    public void setUp() throws Exception {
//        this.iReport = new IReportImpl();
    }

    @After
    public void tearDown() throws Exception {
        this.iReport = null;
    }

    @Test
    public void testdoSalaryReport() throws Exception {

        Employee mainHead = new HeadOfSalesmen("mainHead","mainHead","mainHead", new Money(10, 99));

        List<Employee> salesmanList = Generator.generateSalesmanList(2);
        salesmanList.get(0).setSalary(new Money(1, 1));
        salesmanList.get(1).setSalary(new Money(2, 20));

        mainHead.setSubordinateList(salesmanList);

        Money salary = iReport.doSalaryOfDepartmentReport(mainHead,false);
        Money salaryAll = iReport.doSalaryOfDepartmentReport(mainHead,true);

        Assert.assertEquals(new Money(10, 99), salary);
        Assert.assertEquals(new Money(14, 20), salaryAll);
    }

    @Test
    public void testCurrentEmploee(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new HeadOfSalesmen("1", "1", "1", new Money(10,99)));
        employeeList.add(new Salesman("2", "2", "2", new Money(10,99)));
        employeeList.add(new Salesman("3", "3", "3", new Money(10,99)));
        employeeList.add(new Salesman("5", "4", "4", new Money(10,99)));

        System.out.println(currentEmploee(employeeList, -1));



    }

    private Employee currentEmploee(List<Employee> employeeList, int i) {
        Employee employee = null;
        if (employeeList == null || employeeList.isEmpty() || i >= employeeList.size()) return employee;

        return currentEmploee(employeeList, i+1);
    }




}