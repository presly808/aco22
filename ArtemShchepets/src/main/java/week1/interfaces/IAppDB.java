package week1.interfaces;

import week1.model.Bill;
import week1.model.Product;

import java.util.List;

public interface IAppDB {

    List<Bill> getAllBills();

    List<Product> getAllProducts();

    Bill findByBillId(int billId);
    Product findByProductId(int productId);

    Bill saveBill(Bill bill);
    Product saveProduct(Product product);

    Bill removeBill(int billId);
    Product removeProduct(int productId);

    Bill update(Bill bill);

}
