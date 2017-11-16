package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;

public interface ISalesman {
    Bill createBill(Terminal terminal, Salesman salesman);
    boolean addProduct(Terminal terminal, Bill bill, Product product);
    boolean closeAndSafeBill(Terminal terminal, Bill bill);
    Salesman login(String login, String password);
    Salesman create(String fullName, String login, String password);
}
