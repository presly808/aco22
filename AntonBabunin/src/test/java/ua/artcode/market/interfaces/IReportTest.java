package ua.artcode.market.interfaces;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.IAppDbImpl;
import ua.artcode.market.controllers.IReportImpl;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Department;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.HeadOfSalesmen;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.models.money.Money;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class IReportTest {
    private IAppDb iAppDb;

    @Before
    public void setUp() throws Exception {
        this.iAppDb = new IAppDbImpl();
    }

    @After
    public void tearDown() throws Exception {
        this.iAppDb = null;
    }

    @Test
    public void testdoEmployeeSalaryReport() throws Exception {

        Department department = new Department(new ArrayList<>());

        Employee headOfSalesmen1 = new HeadOfSalesmen("head-1", "head1", "head",
                new Money(100,0));
        Employee headOfSalesmen2 = new HeadOfSalesmen("head-2", "head2", "head",
                new Money(10,0));

        Employee saller1 = new Salesman("1", "1", "1", new Money(1,1));
        department.getEmployeeList().add(headOfSalesmen1);
        department.getEmployeeList().add(headOfSalesmen2);
        department.getEmployeeList().add(saller1);


        headOfSalesmen1.getSubordinateList().add(headOfSalesmen2);
        headOfSalesmen2.getSubordinateList().add(saller1);

        Bill bill1 = new Bill();
        bill1.setAmountPrice(new Money(1000,0));
        bill1.setSalesman(headOfSalesmen1);
        iAppDb.saveBill(bill1);

        Bill bill2 = new Bill();
        bill2.setAmountPrice(new Money(1000,0));
        bill2.setSalesman(saller1);
        iAppDb.saveBill(bill2);

        IReport iReport = new IReportImpl(iAppDb);

        System.out.println("Bill size - " + iAppDb.getBills().size());

        Money money = iReport.calculateSalary(headOfSalesmen1, null, null);
        System.out.println(money);

        assertEquals(new Money(170,0),  money);
        Money all = iReport.doDepartmentReport(department.getEmployeeList(), 0, null, null);
        assertEquals(new Money(251,1),  all);


    }
}