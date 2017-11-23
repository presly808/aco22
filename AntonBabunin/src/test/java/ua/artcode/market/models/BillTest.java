package ua.artcode.market.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.TerminalControllerFactory;
import ua.artcode.market.interfaces.ITerminalController;
import ua.artcode.market.utils.Generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class BillTest {

    private ITerminalController terminalController;

    @Before
    public void setUp() throws Exception {
        this.terminalController = TerminalControllerFactory.create();
    }

    @After
    public void tearDown() throws Exception {
        this.terminalController = null;
    }

    @Test
    public void testPreLambdaSort() throws IOException {
        List<Bill> bills = new ArrayList<>();

        Bill b1 = terminalController.createBill();
        terminalController.addProduct(b1.getId(), Generator.createProduct());
        terminalController.addProduct(b1.getId(), Generator.createProduct());
        terminalController.addProduct(b1.getId(), Generator.createProduct());
        terminalController.addProduct(b1.getId(), Generator.createProduct());

        Bill b2 = terminalController.createBill();;
        terminalController.addProduct(b2.getId(), Generator.createProduct());
        terminalController.addProduct(b2.getId(), Generator.createProduct());
        terminalController.addProduct(b2.getId(), Generator.createProduct());
        terminalController.addProduct(b2.getId(), Generator.createProduct());


        Bill b3= terminalController.createBill();;
        terminalController.addProduct(b3.getId(), Generator.createProduct());

        bills.add(b1);
        bills.add(b2);
        bills.add(b3);

        bills.sort((b5, b4) -> String.valueOf(b1.getAmountPrice()).compareTo(String.valueOf(b2.getAmountPrice())));



    }

}