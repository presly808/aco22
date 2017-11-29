package ua.artcode.market.utils;

import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.models.money.Money;
//import ua.artcode.market.models.money.Salary;

import java.util.*;

public class Generator {
    public static Product createProduct() {
        Product product = new Product();
        product.setName(generateName(30));
        product.setPrice(Double.parseDouble(generateProductPrice()));
        return product;
    }

    private static String generateProductPrice() {
        return (int)(Math.random()*1000) + "." + (int)(Math.random()*100);
    }

    private static String generateName(int r){
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < r; i++) {
            name.append((char) ((int) (Math.random() * 25) + 65));
        }
        return name.toString();
    }

    private static Integer random() {
        return (int)(Math.random()*300);
    }

    public static Map<Product, Integer> randomProducts(int n){
        Map<Product, Integer> products = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Product p = Generator.createProduct();
            p.setId(i);
            products.put(p, Generator.random());
        }
        return products;
    }

    public static List<Employee> generateSalesmanList(int size) {
        List<Employee> salesmanList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Salesman salesman = Generator.createRandomSalesman();
            salesmanList.add(salesman);
        }
        return salesmanList;
    }

    private static Salesman createRandomSalesman() {
        Salesman salesman = new Salesman();
        salesman.setFullName(generateName(10));
        salesman.setLogin(generateName(5));
        salesman.setPassword(generateName(6));
        salesman.setSalary(new Money((int)(Math.random()*1000), (int)(Math.random()*99)));
        return salesman;
    }

    public static List<Employee> CreateSubordinateList() {
        return new ArrayList<Employee>();
    }

    /*public static Terminal createTerminal() {
        return new Terminal((int)(Math.random()*100));

    }*/
}
