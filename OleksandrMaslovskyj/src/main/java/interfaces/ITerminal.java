package interfaces;

import models.Bill;
import models.Product;
import models.Salesman;

import java.util.List;
import java.util.Set;

public interface ITerminal {

    Bill createBill(Bill bill);

    Product addProduct(Bill bill, String productName);

    void closeAndSaveBill(Bill bill);

    Bill findBillById(long id);

    Salesman findSalesmanByLoginOrFullName(String fullname);

    List<Bill> sortBillListByDateCreation();

}
