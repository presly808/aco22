package ua.artcode.market.interfaces;

import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;

public interface ITerminal {

    Bill createBill(String nameSaler, Product[] productsList);
    int questionCreateBill();

}
