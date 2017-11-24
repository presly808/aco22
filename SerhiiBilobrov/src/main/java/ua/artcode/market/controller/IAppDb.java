package ua.artcode.market.controller;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;

import java.util.List;

/**
 * Created by serhii on 19.11.17.
 */
public interface IAppDb {

    List<Bill> getAllBills();
    List<Product> getAllProducts();


    Bill findByBillId(int billId);
    Product findByProductId(int billId);

    Bill saveBill(Bill bill);
    Product saveProduct(Product product);

    Bill removeBill(int bill);
    Product removeProduct(int remove);

    Bill update(Bill bill);



}
