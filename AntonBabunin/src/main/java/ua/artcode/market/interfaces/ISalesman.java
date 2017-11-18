package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;

public interface ISalesman {
    Bill createBill(Terminal terminal, Salesman salesman);
    boolean addProduct(Terminal terminal, Bill bill, Product product);
    Salesman login(Terminal terminal, String login, String password);
    boolean closeAndSafeBill(Terminal terminal, Bill bill);
}
