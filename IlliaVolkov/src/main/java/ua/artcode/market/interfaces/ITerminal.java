package ua.artcode.market.interfaces;

import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;
import ua.artcode.market.model.SalesMan;

import java.util.List;

public interface ITerminal {

    Bill createBill(SalesMan nameSaler, List<Product> productsList);

    void saveBill(Bill currentBill);

    int countQuontityBills();

}
