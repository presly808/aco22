package week1.interfaces;

import week1.models.Bill;
import week1.models.Product;
import week1.models.Salesman;

import java.util.Comparator;
import java.util.List;

/**
 * Created by ENIAC on 19.11.2017.
 */
public interface ITerminalController {

    IAppDb getDb();

    List<Bill> getAllBills();

    List<Product> getAllProducts();

    void login(String login, String pass);

    Bill createBill();

    Bill addProduct(int billId, Product product);

    Bill closeBill(int id);

    Bill findBillById(int billId);

    Salesman findSalesmanByLogin(String Login);

    Salesman getTopOfSalesmans();

    List<Bill> filterForBills(String start, String end, Comparator<Bill> comparator);

    int getCurrentSalesmanIndex();

    void setCurrentSalesmanIndex(int i);
}

