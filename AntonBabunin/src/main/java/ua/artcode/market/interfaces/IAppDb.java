package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IAppDb {

    Map<Product, Integer> getProducts();

    List<Bill> getBills();
    Map<Product, Integer> getAllProducts();
    Bill findBillById(int id);

    Product findProductById(int id);

    Bill removeBill(int id);

    Product removeProduct(int id);
    Bill saveBill(Bill bill);

    Product saveProduct(Product product);
    Bill update(Bill bill);

    Salesman createSalesman(String fullName, String login, String password) throws IOException;
    Salesman login(String login, String password) throws IOException;
    Salesman logout(Salesman salesman) throws IOException;
    Salesman findSalesmanByLogin(String login);




}
