package week1.interfaces;

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

    List<Product> getAllProducts();

    Product findByProductId(int productId);

    List<Salesman> getAllSalesMans();

    Salesman findSalesmanByLogin(String login);

    int getBillNextId();
    int setBillNextId(int i);

}
