package artcode.shop.creator;

import artcode.shop.salesman.Salesman;
import artcode.shop.bill.Bill;
import artcode.shop.product.Product;
import artcode.shop.terminal.Terminal;

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

        return new Bill(id, products, salesman, amountPrice, closeTime, isClosed, firstFreePositionAtProducts, nextFreePositionAtProducts);
    }

    private static int generateID() {
        Terminal.setCountCreatedBill(Terminal.getCountCreatedBill()+1);
        return Terminal.getCountCreatedBill();
    }


}
