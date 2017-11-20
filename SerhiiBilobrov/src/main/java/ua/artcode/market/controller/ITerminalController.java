package ua.artcode.market.controller;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;

import java.util.List;

/**
 * Created by serhii on 19.11.17.
 */
public interface ITerminalController {



    Bill createBill();

    Bill addProduct(int billId, Product product);

    List<Bill> getAllBills();

    Bill closeBill(int id);


}
