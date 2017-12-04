package ua.artcode.market.controllers;

import ua.artcode.market.controllers.AppDBImpl;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;
import ua.artcode.market.model.ProductBill;
import ua.artcode.market.model.Terminal;

import java.io.IOException;

public class Generator{

    public void initSalesMans(int countSalesMans) throws IOException {

        for (int i = 1; i <= countSalesMans; i++) {
            AppDBImpl.getEntity().createSalesMan("SalesMan" + i, "SM" + i, "" + i);
        }
    }

    public void initProductsPrice(int countProducts) throws IOException {

        for (int i = 0; i < countProducts; i++) {
            AppDBImpl.getEntity().createProduct(i + 1,
                    "Goods" + (i + 1),
                    Math.rint((Math.random() * 10) * 100) / 100);
        }
    }

    public void initBill(Terminal currentTerminal, int countBill) throws IOException {

        AppDBImpl appDB = AppDBImpl.getEntity();

        for (int i = 0; i < countBill; i++) {
            Bill currentBill = appDB.createBill(currentTerminal);
            for (Product product: appDB.getProductsPrice()) {
                currentBill.addProductBill(new ProductBill(product.code, 1));
            }
            appDB.billController.closeBill(currentBill);
            appDB.saveClosedBill(currentBill);
        }
    }
}
