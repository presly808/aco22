package week1.controller;

import week1.model.Bill;
import week1.model.Product;
import week1.model.Salesman;

import java.util.List;

/**
 * Created by ENIAC on 19.11.2017.
 */
public interface IAppDb {

    List<Bill> getAllBills();
    List<Product> getAllProducts();
    List<Salesman> getAllSalesMans();


    Bill findByBillId(int billId);
    Product findByProductId(int billId);
    Salesman findBySalesmanId(int billId);



    Bill saveBill(Bill bill);
    Product saveProduct(Product product);

    Bill removeBill(int bill);
    Product removeProductFromBill(Bill bill, int remove);

    Bill update(Bill bill);



}
