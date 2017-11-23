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

    Bill findByBillId(int billId);

    Bill update(Bill bill);

    Bill saveBill(Bill bill);

    Bill removeBill(int bill);


    List<Salesman> getAllSalesMans();

    Salesman findSalesmanByLogin(String login);

    List<Product> getAllProducts();

    Product findByProductId(int productId);






//    List<Product> getAllProducts();
//    Product saveProduct(Product product);
//    Product findByProductId(int billId);
//    Product removeProductFromBill(int billid, int productId);
}
