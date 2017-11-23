package ua.artcode.market.interfaces;


import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Terminal;

public interface ITerminal {
    Bill createBill(Terminal terminal, Salesman salesman);
    boolean addProduct(Bill bill, Product product);
    boolean closeAndSafeBill(Bill bill);
    Salesman login(Terminal terminal, String login, String password);
    Salesman create(String fullName, String login, String password);
}