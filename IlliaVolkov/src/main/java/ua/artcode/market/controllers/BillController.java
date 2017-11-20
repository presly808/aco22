package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IBill;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;
import ua.artcode.market.view.InterfaceServices;

import java.util.Date;

public class BillController implements IBill{

    public static void addProduct(Bill currentBill, int productCode, int quantity){

        boolean productProcessed = false;

        for (int i = 0; i < currentBill.getProducts().length; i++) {

            if ((currentBill.getProducts()[i][0] == 0 || currentBill.getProducts()[i][0] == productCode) && !productProcessed) {

                currentBill.setQuantityGoods((currentBill.getProducts()[i][0] == 0 ? (currentBill.getQuantityGoods() + 1) : currentBill.getQuantityGoods()));

                currentBill.setProducts(i,0, productCode);
                currentBill.setProducts(i,1, quantity + currentBill.getProducts()[i][1]);

                currentBill.setAmountPrice(currentBill.getAmountPrice() + Math.rint(Product.findByCode( currentBill.getProductList(), productCode).price*currentBill.getProducts()[i][1]*100)/100);

                productProcessed = true;
            }
        }

        if (!productProcessed) {
            System.out.println("The limit of the number of products in the Check has been reached " +
                    currentBill.getProducts().length + " pieces");
        }
    }

    public static void changeProduct(Bill currentBill, int productCode, int quantity){

        boolean productProcessed = false;

        for (int i = 0; i < currentBill.getProducts().length; i++) {

            if (currentBill.getProducts()[i][0] == productCode && !productProcessed) {

                if (quantity == 0) {
                    currentBill.setProducts(i,0, 0);
                    currentBill.setProducts(i,1, 0);
                    currentBill.setQuantityGoods(currentBill.getQuantityGoods() - 1);

                    calculateAmountPrice(currentBill);

                    productProcessed = true;
                }
                else {
                    currentBill.setProducts(i,0, productCode);
                    currentBill.setProducts(i,1, quantity);

                    calculateAmountPrice(currentBill);

                    productProcessed = true;
                }
            }
        }
    }

    @Override
    public void choseProduct(Bill currentBill) {

        InterfaceServices.choseProduct(currentBill);
    }

    public static String getBillInfoForPrint(Bill currentBill){

        String message = "Bill №" + currentBill.getCode() +
                "/ quontity of goods - " + currentBill.getQuantityGoods() +
                "/ Amount - " + currentBill.getAmountPrice() +
                "/  SalesMan - " + currentBill.getSalesMan().getFullName() +
                "/ Closed - " + currentBill.closed +
                "/ CloseTime - " + currentBill.closeTime;

        return message;
    }

    public static String GetProductsForPrint(Bill currentBill){

        String message = "Code\t"+"Goods\t"+"Price\t"+"Quantity\n";

        for (int i = 0; i < currentBill.getProducts().length; i++) {

            if (currentBill.getProducts()[i][0] != 0) {

                Product currentProduct = Product.findByCode(currentBill.getProductList(), currentBill.getProducts()[i][0]);
                message += "" + currentProduct.code +
                        "\t\t" + currentProduct.name +
                        "\t\t" + currentProduct.price +
                        "\t\t" + currentBill.getProducts()[i][1]+"\n";
            }
        }

        return message;
    }

    public static void calculateAmountPrice(Bill currentBill){

        currentBill.setAmountPrice(0);

        for (int i = 0; i < currentBill.getProducts().length; i++) {


            if (currentBill.getProducts()[i][0] != 0) {
                Product currentProduct = Product.findByCode(currentBill.getProductList(), currentBill.getProducts()[i][0]);
                currentBill.setAmountPrice(Math.rint(currentBill.getAmountPrice() + currentProduct.price*currentBill.getProducts()[i][1]*100)/100);
            }
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
