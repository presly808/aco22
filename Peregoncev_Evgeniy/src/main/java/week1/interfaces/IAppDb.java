package week1.interfaces;

import week1.models.Bill;
import week1.models.Product;
import week1.models.Salesman;

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

    List<Product> getAllProducts();

    Product findByProductId(int productId);

    List<Salesman> getAllSalesMans();

    Salesman findSalesmanByLogin(String login);

}
