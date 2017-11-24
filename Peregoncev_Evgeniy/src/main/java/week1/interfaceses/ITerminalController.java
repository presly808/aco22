package week1.interfaceses;

import week1.model.Bill;
import week1.model.Product;
import week1.model.Salesman;

import java.util.Comparator;
import java.util.List;

/**
 * Created by ENIAC on 19.11.2017.
 */
public interface ITerminalController {

    void login(String login, String pass);

    Bill createBill();

    List<Bill> getAllBills();

    Bill addProduct(int billId, Product product);

    Bill closeBill(int id);

    Bill findBillById(int billId);

    List<Bill> filterForBills(String start, String end, Comparator<Bill> comparator);

    List<Product> getAllProducts();

    Salesman findSalesmanByLogin(String Login);

    Salesman getTopOfSalesmans();

}

