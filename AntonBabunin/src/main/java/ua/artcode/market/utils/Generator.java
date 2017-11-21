package ua.artcode.market.utils;

import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            name.append((char) ((int) (Math.random() * 65) + 25));
        }
        return name.toString();
    }

    private static Integer random() {
        return (int)(Math.random()*300);
    }

    public static Map<Product, Integer> randomProducts(){
        Map<Product, Integer> products = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            products.put(Generator.createProduct(), Generator.random());
        }
        return products;
    }

    public static List<Salesman> generateSalesmanList(int size) {
        List<Salesman> salesmanList = new ArrayList<>();
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
        return salesman;
    }

    /*public static Terminal createTerminal() {
        return new Terminal((int)(Math.random()*100));

    }*/
}
