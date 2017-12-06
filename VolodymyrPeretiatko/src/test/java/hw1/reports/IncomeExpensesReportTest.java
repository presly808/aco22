package hw1.reports;

import org.junit.*;

import java.util.Date;

public class IncomeExpensesReportTest {

    IncomeExpensesReport report1, report2, report3;

    @Before
    public void testInit(){

        Date start = new Date(1510842400000L);
        Date end = new Date(1510842409999L);

        report1 = new IncomeExpensesReport(start, end, 100.0, 20.0);
        report2 = new IncomeExpensesReport(start, end, 100.0, 20.0);
        report3 = new IncomeExpensesReport(start, end, 140.0, 24.0);
    }

    @Test
    public void equalsTest() throws Exception {
        Assert.assertTrue(report1.equals(report2));
        Assert.assertFalse(report1.equals(report3));
    }

    @Test
    public void hashCodeTest() throws Exception {
        Assert.assertTrue(report1.hashCode() == report2.hashCode());
        Assert.assertFalse(report1.hashCode() == report3.hashCode());
    }
}
