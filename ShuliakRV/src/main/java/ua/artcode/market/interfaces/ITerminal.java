package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.models.Statistic;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public interface ITerminal {

    Salesman logIn(String login, String password);

    Bill createBill(Salesman s);

    Bill addProduct(int billId, Product product);

    Bill closeAndSaveBill(int billId);

    List<Salesman> getTopNOfSalesMen(int n);

    Statistic doSomeStatisticStuff();

    List<Bill> filter(List<Salesman> salesmen, List<Product> products,
                      LocalDateTime startTime, LocalDateTime endTime,
                      Comparator<Bill> comparator);
}
