package ua.artcode.market;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    @Test
    public void createProduct() throws Exception {
        Product newProduct = Product.createProduct(1, "1", 1.1);

        Assert.assertEquals(1, newProduct.code);
        Assert.assertEquals( "1", newProduct.name);
        Assert.assertEquals( 1.1, newProduct.price, 0);
    }

    @Test
    public void initProductsList() throws Exception {
        Product[] newProductList = Product.initProductsList(5);

        Assert.assertEquals(new Product[5].length, newProductList.length);
    }

    @Test
    public void findByCode() throws Exception {
        Product[] newProductList = Product.initProductsList(5);

        Product.findByCode(newProductList, 3);

        Assert.assertEquals(3, Product.findByCode(newProductList, 3).code);
    }

    @Test
    public void printFullInfo() throws Exception {
        Product[] newProductList = Product.initProductsList(5);

        Assert.assertNotEquals("", Product.fulInfoOfProdukts(newProductList));
    }

}