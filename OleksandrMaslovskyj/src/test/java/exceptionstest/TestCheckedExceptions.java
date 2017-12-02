package exceptionstest;

import exceptions.*;
import org.junit.Before;
import org.junit.Test;

public class TestCheckedExceptions {

    @Before
    public void prepareData(){}

    @Test(expected = BillNotFoundException.class)
    public void testBillNotFoundException(){

    }

    @Test(expected = IncorrectBillException.class)
    public void testIncorrectBillException(){

    }

    @Test(expected = InvalidSalesmanException.class)
    public void testInvalidSalesmanException(){

    }

    @Test(expected = NoSuchSalesmanException.class)
    public void testNoSuchSalesmanException(){

    }

    @Test(expected = UnableToAddProductToBillException.class)
    public void testUnableToAddProductToBillException(){

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
