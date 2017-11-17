package week1.interfaces;

import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;

public interface ITerminal {

    void signIn(String login, String password);

    boolean createBill(Bill newBill);

    boolean addProductToBill(Product newProduct);

    boolean closeAndSaveBill();

    Bill findBillById(int searchingId);

    Seller findSalesmanByLoginOrFullname(String loginOrNameOfSalesMan);

    Seller[] getTopNofSalesMan(int quantityOfTopSellers);

    String doSomeStatisticStuff();
}
