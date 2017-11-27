package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IBill;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;
import ua.artcode.market.model.ProductBill;
import ua.artcode.market.view.InterfaceServices;

import java.util.List;
import java.util.Date;

public class BillController implements IBill{

    public static void addProduct(Bill currentBill, int productCode, int quantity){

        boolean productProcessed = false;

        for (ProductBill itemProductBill: currentBill.getProductsBill()) {

            if (itemProductBill.getProductCode() == productCode && !productProcessed) {

                itemProductBill.setProductQuontity(itemProductBill.getProductQuontity() + quantity);

                calculateAmountPrice(currentBill);

                productProcessed = true;
            }
        }

        if (!productProcessed) {
            currentBill.setProductsBill(new ProductBill(Product.findByCode( currentBill.getProductsPrice(), productCode).code, quantity));

            calculateAmountPrice(currentBill);
            currentBill.setQuantityGoods(currentBill.getQuantityGoods() + 1);
        }
    }

    public static void changeProduct(Bill currentBill, int productCode, int quantity){

        boolean productProcessed = false;

        List<ProductBill> currentProductsBill = currentBill.getProductsBill();
        for (ProductBill itemProductBill: currentProductsBill) {

            if (itemProductBill.getProductCode() == productCode && !productProcessed) {

                if (quantity == 0) {

                    currentProductsBill.remove(itemProductBill);
                    currentBill.setQuantityGoods(currentBill.getQuantityGoods() - 1);
                    calculateAmountPrice(currentBill);
                }
                else {
                    itemProductBill.setProductQuontity(quantity);
                    calculateAmountPrice(currentBill);
                }
                productProcessed = true;
            }
        }
    }

    @Override
    public void choseProduct(Bill currentBill) {

        InterfaceServices.choseProduct(currentBill);
    }

    public static String getBillHeadInfoForPrint(Bill currentBill){

        String message = "Bill №" + currentBill.getCode() +
                "/ quontity of goods - " + currentBill.getQuantityGoods() +
                "/ Amount - " + currentBill.getAmountPrice() +
                "/ SalesMan - " + currentBill.getSalesMan().getFullName() +
                "/ Closed - " + currentBill.closed +
                "/ createTime - " + (currentBill.createTime == null ? "" : currentBill.createTime) +
                "/ CloseTime - " + (currentBill.closeTime == null ? "" : currentBill.closeTime);

        return message;
    }

    public static String GetBillProductsForPrint(Bill currentBill){

        String message = "Code\t"+"Goods\t"+"Price\t"+"Quantity\n";

        for (ProductBill itemProductBill: currentBill.getProductsBill()) {

            Product currentProduct = Product.findByCode(currentBill.getProductsPrice(),
                    itemProductBill.getProductCode());

            message += "" + currentProduct.code +
                    "\t\t" + currentProduct.name +
                    "\t\t" + currentProduct.price +
                    "\t\t" + itemProductBill.getProductQuontity()+"\n";
        }

        return message;
    }

    public static void calculateAmountPrice(Bill currentBill){

        currentBill.setAmountPrice(0);

        for (ProductBill itemProductBill: currentBill.getProductsBill()) {

            Product currentProduct = Product.findByCode(currentBill.getProductsPrice(),
                    itemProductBill.getProductCode());

            currentBill.setAmountPrice(Math.rint(currentBill.getAmountPrice()
                    + currentProduct.price*itemProductBill.getProductQuontity()*100)/100);
        }
    }

    @Override
    public void questionForClosingBill(Bill currentBill) {

        InterfaceServices.questionForClosingBill(currentBill);
    }

    public static void closeBill(Bill currentBill){

        currentBill.closed = true;
        currentBill.closeTime = new Date();
    }

    @Override
    public void allProductsSelected(Bill currentBill) {

        InterfaceServices.allProductsSelected(currentBill);
    }
}
