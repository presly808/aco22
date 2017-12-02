package ua.artcode.market.Util;

import ua.artcode.market.controllers.AppDBImpl;
import ua.artcode.market.controllers.BillController;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;
import ua.artcode.market.model.ProductBill;
import ua.artcode.market.model.Terminal;

import java.io.IOException;

public class Generator{

    AppDBImpl currentAppDBImpl;

    public Generator(AppDBImpl currentAppDBImpl) {
        this.currentAppDBImpl = currentAppDBImpl;
    }

    public void initSalesMans(int countSalesMans){

        for (int i = 1; i <= countSalesMans; i++) {
           this.currentAppDBImpl.createSalesMan("SalesMan"+i, "SM"+i, ""+i);
        }
    }

    public void initProductsPrice(int countProducts){

        for (int i = 0; i < countProducts; i++) {
            this.currentAppDBImpl.createProduct(i+1,
                    "Goods"+(i+1),
                    Math.rint((Math.random()*10)*100)/100);
        }
    }

    public void initBill(Terminal currentTerminal, int countBill) throws IOException {

        for (int i = 0; i < countBill; i++) {
            Bill currentBill = this.currentAppDBImpl.createBill(currentTerminal);
            for (Product product: this.currentAppDBImpl.getProductsPrice()) {
                currentBill.addProductBill(new ProductBill(product.code, 1));
            }
            currentBill.terminal.currentAppDBImpl.billController.closeBill(currentBill);
            currentAppDBImpl.saveClosedBill(currentBill);
        }
    }
}
