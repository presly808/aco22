package ua.artcode.market.creator;


import ua.artcode.market.bill.Bill;
import ua.artcode.market.product.Product;
import ua.artcode.market.salesman.Salesman;

public class BillCreator {

    private static final int DEFAULT_PRODUCTS_SIZE = 20;

    public static Bill createBill (Salesman salesman) {
        int id = generateID();
        Product[] products = new Product[DEFAULT_PRODUCTS_SIZE];
        double amountPrice = 0.0;
        String closeTime = "";
        boolean isClosed = false;
        int firstFreePositionAtProducts = 0;
        int nextFreePositionAtProducts =1;

        int addedProduct = 0;

        return new Bill(id, products, salesman, amountPrice, closeTime, isClosed, firstFreePositionAtProducts, nextFreePositionAtProducts);
    }

    private static int generateID() {

        return (int) (Math.random()*10000);
    }


}
