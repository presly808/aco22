package main.java.interfaces;

import main.java.controllers.BillController;
import main.java.models.Product;
import main.java.models.Salesman;

public interface ITerminal {

    boolean login();

    BillController createBill();

    Product addProduct(String productName);

    void closeAndSaveBill(BillController bill);

    BillController findBillById(long id);

    Salesman findSalesmanByLoginOrFullName(String fullname);

    Salesman getTopOnSalesMan();

}
