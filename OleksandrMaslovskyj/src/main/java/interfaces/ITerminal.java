package interfaces;

import controllers.BillController;
import models.Product;
import models.Salesman;

public interface ITerminal {

    boolean login();

    BillController createBill();

    Product addProduct(String productName);

    void closeAndSaveBill(BillController bill);

    BillController findBillById(long id);

    Salesman findSalesmanByLoginOrFullName(String fullname);

    Salesman getTopOnSalesMan();

}
