package controllers;

import implementation.Bill;
import models.Product;
import models.Salesman;

public interface TerminalController {

    boolean login();

    Bill createBill();

    Product addProduct(String productName);

    void closeAndSaveBill(Bill bill);

    Bill findBillById(long id);

    Salesman findSalesmanByLoginOrFullName(String fullname);

    Salesman getTopOnSalesMan();

}
