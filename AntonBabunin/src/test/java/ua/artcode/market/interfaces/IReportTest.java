package ua.artcode.market.interfaces;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.IReportImpl;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.HeadOfSalesmen;
import ua.artcode.market.models.money.Money;
//import ua.artcode.market.models.money.Salary;
import ua.artcode.market.utils.Generator;

import java.util.List;

public class IReportTest {

    private IReport iReport;

    @Before
    public void setUp() throws Exception {
        this.iReport = new IReportImpl();
    }

    @After
    public void tearDown() throws Exception {
        this.iReport = null;
    }

    @Test
    public void doSalaryReport() throws Exception {

        Employee mainHead = new HeadOfSalesmen("mainHead","mainHead","mainHead", new Money(10, 99));

        List<Employee> salesmanList = Generator.generateSalesmanList(2);
        salesmanList.get(0).setSalary(new Money(1, 1));
        salesmanList.get(1).setSalary(new Money(2, 20));

        mainHead.setSubordinateList(salesmanList);

        Money salary = iReport.doSalaryReport(mainHead,false);
        Money salaryAll = iReport.doSalaryReport(mainHead,true);

        Assert.assertEquals(new Money(10, 99), salary);
        Assert.assertEquals(new Money(14, 20), salaryAll);
    }

}