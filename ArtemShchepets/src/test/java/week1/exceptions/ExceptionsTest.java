package week1.exceptions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import week1.comparators.CreationDateComparator;
import week1.controller.ISellerController;
import week1.controller.ISellerControllerImpl;
import week1.controller.ITerminalController;
import week1.controller.ITerminalControllerFactory;
import week1.model.Product;

import java.time.LocalDateTime;


public class ExceptionsTest {

    private ITerminalController terminalController;
    private ISellerController sellerController;

    @Before
    public void setUp() {
        terminalController = ITerminalControllerFactory.create();
        sellerController = new ISellerControllerImpl();
    }

    @After
    public void tearDown() {
        terminalController = null;
    }

    @Test(expected = InvalidBillIdException.class)
    public void testInvalidBillIdException() throws InvalidBillIdException, UnableToFindABillException {
        terminalController.addProduct(-123, new Product());
    }

    @Test(expected = UnableToCalcucateDeptCostsException.class)
    public void testUnableToCalculateDeptCostException() throws UnableToCalcucateDeptCostsException {
        sellerController.calculateDepartamentCosts(null);
    }

    @Test(expected = Exception.class)
    public void testUnableToCalculateBillIncomeException()
            throws UnableToCalculateBillIncomeException, UnableToCalcucateDeptCostsException {
        Exception exception = new UnableToCalculateBillIncomeException("test");
        sellerController.calculateIncomeAndExpenses(null);
    }

    @Test(expected = Exception.class)
    public void testUnableToCalculateSellerSalaryException() throws UnableToCalculateSellerSalaryException {
        sellerController.calculateAllSellerSalary(null);
    }

    @Test(expected = UnableToDoStatisticException.class)
    public void testUnableToDoStatisticException() throws UnableToDoStatisticException, UnableToGetTopSellersException {
        terminalController.doSomeStatisticStuff();
    }

    @Test(expected = UnableToFilterException.class)
    public void testUnableToFilterException() throws UnableToFilterException {
        terminalController
                .filter(LocalDateTime
                        .parse("2007-12-01T10:15:30"),
                        LocalDateTime.parse("2003-12-01T10:15:30"), new CreationDateComparator());
    }

    @Test(expected = UnableToFindABillException.class)
    public void testUnableToFindABillException() throws InvalidBillIdException, UnableToFindABillException {
        terminalController.addProduct(22, new Product());
    }

    @Test(expected = UnableToGetTopSellersException.class)
    public void testUnableToGetTopSellersException() throws UnableToGetTopSellersException {
       terminalController.getTopOfSalesman();
    }

    @Test(expected = UnableToLogInException.class)
    public void testUnableToLogInException() throws UnableToLogInException {
        terminalController.login(null, "");
    }
}
