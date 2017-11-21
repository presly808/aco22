package ua.artcode.market.interfaces;

import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;

import java.io.IOException;
import java.util.List;

public interface ITerminalController {

    Bill createBill() throws IOException;

    Bill addProduct(int billId, Product product) throws IOException;

    List<Bill> getAllBills();

    double calculateAmountPrice(Bill bill);

    String prinBill(Bill bill);

    Bill closeBill(int id) throws IOException;

    IAppDb getiAppDb();

    Salesman createSalesman(String fullName, String login, String password) throws IOException;
    Salesman login(String login, String password) throws IOException;
    Salesman logout(Salesman salesman) throws IOException;
    Salesman findSalesmanByLogin(String login);
}
