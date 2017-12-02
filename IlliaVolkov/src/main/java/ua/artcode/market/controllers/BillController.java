package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IBill;
import ua.artcode.market.model.*;
import ua.artcode.market.view.InterfaceServices;

import java.util.Date;

public class BillController implements IBill{

    public static Bill createBill(Terminal currentTerminal) {
        return currentTerminal.currentAppDBImpl.createBill(currentTerminal);
    }

    public static void addProduct(Bill currentBill, int productCode, int quantity){
        AppDBImpl.addProductToBill(currentBill, productCode, quantity);
    }

    public static void changeProductToBill(Bill currentBill, int productCode, int quantity){
        AppDBImpl.changeProductToBill(currentBill, productCode, quantity);
    }

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

            Product currentProduct = currentBill.terminal.currentAppDBImpl.findProductByCode(itemProductBill.getProductCode());

            message += "" + currentProduct.code +
                    "\t\t" + currentProduct.name +
                    "\t\t" + currentProduct.price +
                    "\t\t" + itemProductBill.getProductQuontity()+"\n";
        }

        return message;
    }

    public static void printBill(Bill currentBill) {

        System.out.println(BillController.getBillHeadInfoForPrint(currentBill));
        System.out.println(BillController.GetBillProductsForPrint(currentBill));
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
