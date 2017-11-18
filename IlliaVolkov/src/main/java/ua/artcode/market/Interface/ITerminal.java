package ua.artcode.market.Interface;

import ua.artcode.market.Model.Bill;
import ua.artcode.market.Model.Product;

public interface ITerminal {

    Bill createBill(String nameSaler, Product[] productsList);
    int questionCreateBill();

}
