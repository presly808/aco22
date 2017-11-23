package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.util.List;

public interface IAppDb {

    List<Bill> getAllBills();
    List<Product> getAllProducts();
    List<Salesman> getAllSalesman();

    Bill findByBillId(int billId);
    Product findByProductId(int billId);

    Bill saveBill()(Bill bill);
    Product saveProduct(Product product);

    Bill removeBill(int bill);
    Product removeProduct(int remove);

    Bill update(Bill bill);
}
