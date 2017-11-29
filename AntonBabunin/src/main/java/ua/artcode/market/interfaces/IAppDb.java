package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.employee.Salesman;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public interface IAppDb {

    Map<Product, Integer> getProducts();

    List<Bill> getBills();
    List<Employee> getEmployee();
    Bill findBillById(int id);

    Product findProductById(int id);

    Bill removeBill(int id) throws IOException;

    Product removeProduct(int id) throws IOException;
    Bill saveBill(Bill bill) throws IOException;

    Product saveProduct(Product product) throws IOException;
    Bill update(Bill bill) throws IOException;

    Employee createSalesman(String fullName, String login, String password) throws IOException;
//            throws IOException;

//    Employee login(String login, String password) throws IOException;
//    Employee logout(Employee salesman) throws IOException;
//    Employee findSalesmanByLogin(String login);

    List<Bill> filter(Employee salesman, Product product,
                      LocalDateTime startDate, LocalDateTime endDate,
                      Comparator<Bill> billComparator);


    double aggrAmtPrice(Salesman salesman, LocalDateTime startDate,
                        LocalDateTime endDate);

    double averageAmountPrice(Salesman salesman, LocalDateTime startDate,
                              LocalDateTime endDate);

    Bill minAmountPrice(Salesman salesman, LocalDateTime startDate,
                        LocalDateTime endDate);

    Bill maxAmountPrice(Salesman salesman, LocalDateTime startDate,
                        LocalDateTime endDate);
}
