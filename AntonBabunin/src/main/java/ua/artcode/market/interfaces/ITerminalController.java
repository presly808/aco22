package ua.artcode.market.interfaces;

import ua.artcode.market.models.Terminal;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;

import java.util.List;

public interface ITerminalController {

    Bill createBill();

    Bill addProduct(int billId, Product product);

    List<Bill> getAllBills();

    double calculateAmountPrice(Bill bill);

    String prinBill(Bill bill);

    Bill closeBill(int id);

    IAppDb getiAppDb();
}
