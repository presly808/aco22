package exceptionstest;

import controllers.BillController;
import controllers.SalesmanController;
import controllers.TerminalController;
import exceptions.*;
import interfaces.IBillLogic;
import models.Bill;
import models.Salesman;
import org.junit.Before;
import org.junit.Test;
import utils.StringGenerator;

import java.util.List;

public class TestCheckedExceptions {

    private BillController billLogic;
    private SalesmanController salesmanController;
    private TerminalController terminalController;


    @Before
    public void prepareData(){
        billLogic = new BillController();
        salesmanController = new SalesmanController();
        terminalController = new TerminalController(billLogic);
    }

    @Test(expected = BillNotFoundException.class)
    public void testBillNotFoundException() throws BillNotFoundException {
        Bill bill  = null;
        billLogic.printBill(bill);
    }

    @Test(expected = IncorrectBillException.class)
    public void testIncorrectBillException() throws IncorrectBillException {
        Bill bill = null;
        terminalController.createBill(bill);
    }

    @Test(expected = InvalidSalesmanException.class)
    public void testInvalidSalesmanException() throws InvalidSalesmanException,
                                                    UnableToCalculateSalaryException {
        List<Salesman> salesmanList = null;
        salesmanController.calculateDepartmentCostsToSalary(salesmanList);
    }

    @Test(expected = NoSuchSalesmanException.class)
    public void testNoSuchSalesmanException() throws NoSuchSalesmanException {
        terminalController.findSalesmanByLoginOrFullName(StringGenerator.generateName(),
                                                                StringGenerator.generateName());
    }

    @Test(expected = UnableToAddProductToBillException.class)
    public void testUnableToAddProductToBillException() throws UnableToAddProductToBillException {
        Bill bill = null;
        billLogic.addProductToBill(bill, StringGenerator.generateName());
    }

    @Test(expected = UnableToCalculatePriceException.class)
    public void testUnableToCalculatePriceException(){

    }

    @Test(expected = UnableToCalculateSalaryException.class)
    public void testUnableToCalculateSalaryException(){

    }

    @Test(expected = UnableToGetSubordinatorsException.class)
    public void testUnableToGetSubordinatorsException(){}

    @Test(expected = UnableToGetSubordinatorsException.class)
    public void testUnableToCloseBillException(){}

}
