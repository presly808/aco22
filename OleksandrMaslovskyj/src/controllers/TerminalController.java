package controllers;

import implementation.Bill;
import models.Product;
import models.Salesman;

public interface TerminalController {

    boolean login();

    Bill createBill();

    Product addProduct();

    Bill closeAndSaveBill(Bill bill);

    Bill findBillById(long id);

    Salesman findSalesmanByLoginOrFullname(String fullname);

    Salesman getTopNofSalesMan();

}
