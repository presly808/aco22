package ua.artcode.convert_test;

import com.google.gson.Gson;
import ua.artcode.market.models.Product;

public class TestConvert {
    public static void main(String[] args) {

        Product product = new Product("milk", 9.50, 3);

        Gson gsonFirst = new Gson();
        String json = gsonFirst.toJson(product);
        System.out.println(json);

        String gsonProduct = "{ 'name': 'milk', 'price': 10.50, 'id': 1}";

        Product productGson = new Product();
        productGson = gsonFirst.fromJson(gsonProduct, Product.class);
        System.out.println(productGson);
    }

}
