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

    Salesman findSalesmanByLoginOrFullname(String loginOrFullname);

    Salesman findSalesmanByLoginOAndPassword(String login,
                                             String password);

    Bill saveBill(Bill bill);

    Product saveProduct(Product product);

    Salesman saveSalesman(Salesman salesman);

    Bill removeBill(int bill);

    Product removeProduct(int remove);

    boolean removeSalesman(Salesman salesman);

    Bill updateBill(Bill bill);

    Product updateProduct(Product product);

    Salesman updateSalesman (Salesman salesman);

}
