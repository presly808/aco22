package week1.interfaces;

import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;
import week1.model.Statistic;

import java.util.List;

public interface ITerminalController {

    boolean login(String login, String password);

    Bill createBill();

    Bill addProduct(int billId, Product product);

    List<Bill> getAllBills();

    Bill closeBill(int billId);

    Bill findBillById(int billId);

    Seller findSellerByLoginOrFullName(String loginOrFullName);

    Seller[] getTopNOfSalesman(int quantityOfBestSellers);

    Statistic doSomeStatisticStuff();
}
