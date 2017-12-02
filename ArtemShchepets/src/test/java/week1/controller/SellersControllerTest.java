package week1.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import week1.model.IncomeExpenses;
import week1.model.Seller;
import week1.utils.InitUtils;

import static org.junit.Assert.*;

public class SellersControllerTest {

    private Seller mainSeller;
    private SellersController sellersController;

    @Before
    public void setUp() throws Exception {

        mainSeller = InitUtils.createDepartment();
        sellersController = new SellersController();
    }

    @After
    public void tearDown() throws Exception {

        mainSeller = null;
        sellersController = null;
    }

    @Test
    public void calculateAllSellerSalary() throws Exception {

        assertEquals(27.65, sellersController.calculateAllSellerSalary(mainSeller),0.01);
    }

    @Test
    public void calculateDepartmentCosts() throws Exception {

        assertEquals(123,sellersController.calculateDepartamentCosts(mainSeller),0.001);
    }

    @Test
    public void calculateIncomeAndExpenses() throws Exception {

        IncomeExpenses incomeExpenses = new IncomeExpenses();

        incomeExpenses.setIncome(615.00);

        incomeExpenses.setExpenses(123.00);

        assertEquals(incomeExpenses,sellersController.calculateIncomeAndExpenses(mainSeller));
    }

}