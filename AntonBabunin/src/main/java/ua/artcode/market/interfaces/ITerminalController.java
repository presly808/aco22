package ua.artcode.market.interfaces;

import javafx.concurrent.Worker;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.Salesman;
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



    Employee createSalesman(String fullName, String login, String password) throws IOException;
    Employee login(String login, String password) throws IOException;
    Employee logout(Salesman salesman) throws IOException;
    Employee findSalesmanByLogin(String login);

}
