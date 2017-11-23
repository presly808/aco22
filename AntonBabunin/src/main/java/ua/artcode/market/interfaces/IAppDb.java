package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public interface IAppDb {

    Map<Product, Integer> getProducts();

    List<Bill> getBills();
    Map<Product, Integer> getAllProducts();
    Bill findBillById(int id);

    Product findProductById(int id);

    Bill removeBill(int id) throws IOException;

    Product removeProduct(int id) throws IOException;
    Bill saveBill(Bill bill) throws IOException;

    Product saveProduct(Product product) throws IOException;
    Bill update(Bill bill) throws IOException;

    Salesman createSalesman(String fullName, String login, String password)
            throws IOException;

    Salesman login(String login, String password) throws IOException;
    Salesman logout(Salesman salesman) throws IOException;
    Salesman findSalesmanByLogin(String login);

    List<Bill> filter(Salesman salesman, Product product,
                      LocalDateTime startDate, LocalDateTime endDate,
                      Comparator<Bill> billComparator);



}
