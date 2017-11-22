package hw1.controller;

import hw1.model.Bill;
import hw1.model.Product;
import hw1.model.Salesman;

import java.util.List;
import java.util.Map;

public interface IAppDB {

    Bill createBill();
    Product createProduct();
    Salesman createSalesman();

    Bill findBillById(int id);
    Product findProductById(int id);
    Salesman findSalesmanById(int id);

    Bill updateBill(Bill b);
    Product updateProduct(Product p);
    Salesman updateSalesman(Salesman s);

    List<Bill> getBills();
    List<Salesman> getSalesmen();
    List<Product> getProducts();

}
