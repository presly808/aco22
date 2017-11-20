package week1.interfaces;

import week1.model.Bill;
import week1.model.Product;

import java.util.List;

public interface ITerminalController {

    Bill createBill();

    Bill addProduct(int billId, Product product);

    List<Bill> getAllBills();

    Bill closeBill(int billId);
}
