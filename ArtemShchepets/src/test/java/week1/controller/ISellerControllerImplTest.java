package week1.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import week1.model.IncomeExpenses;
import week1.model.Seller;
import week1.utils.InitUtils;

import static org.junit.Assert.*;

public class ISellerControllerImplTest {

    private Seller mainSeller;
    private ISellerControllerImpl ISellerControllerImpl;

    @Before
    public void setUp() throws Exception {

        mainSeller = InitUtils.createDepartment();
        ISellerControllerImpl = new ISellerControllerImpl();
    }

    @After
    public void tearDown() throws Exception {

        mainSeller = null;
        ISellerControllerImpl = null;
    }

    @Test
    public void calculateAllSellerSalary() throws Exception {

        assertEquals(27.65, ISellerControllerImpl.calculateAllSellerSalary(mainSeller),0.01);
    }

    @Test
    public void calculateDepartmentCosts() throws Exception {

        assertEquals(123, ISellerControllerImpl.calculateDepartamentCosts(mainSeller),0.001);
    }

    @Test
    public void calculateIncomeAndExpenses() throws Exception {

        IncomeExpenses incomeExpenses = new IncomeExpenses();

        incomeExpenses.setIncome(615.00);

        incomeExpenses.setExpenses(123.00);

        assertEquals(incomeExpenses, ISellerControllerImpl.calculateIncomeAndExpenses(mainSeller));
    }

}