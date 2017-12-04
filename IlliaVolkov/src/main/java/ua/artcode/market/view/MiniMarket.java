package ua.artcode.market.view;

import ua.artcode.market.controllers.AppDBImpl;
import ua.artcode.market.factory.MarketFactory;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.SalesMan;
import ua.artcode.market.model.Terminal;
import ua.artcode.market.proxy.MarketProxy;

import javax.swing.*;
import java.io.IOException;

public class MiniMarket {

    public static void main(String[] args) throws IOException {

        MarketProxy marketProxy = MarketFactory.createProxy();

        marketProxy.getMiniMarket().startMarket();
    }

    public void startMarket() throws IOException {

        JOptionPane.showMessageDialog(null, "Good afternoon!\n" + "Shop starts work");

        AppDBImpl.getEntity().generator.initSalesMans(2);

        SalesMan autorizedSalesMan = InterfaceServices.autorizationSalesMan();

        if (autorizedSalesMan != null) {

            Terminal currentTerminal = AppDBImpl.getEntity().createTerminal();
            currentTerminal.setAutorizedSalesMan(autorizedSalesMan);

            String stringCountProducts = JOptionPane.showInputDialog("Enter the number of products in the store", 0);
            int countProducts = Integer.parseInt((stringCountProducts == null ? "0" : stringCountProducts));

            AppDBImpl.getEntity().generator.initProductsPrice(countProducts);

            int key = InterfaceServices.questionCreateBill();

            while (key == JOptionPane.YES_OPTION) {

                Bill currentBill = AppDBImpl.getEntity().createBill(currentTerminal);

                while (!currentBill.closed) {

                    InterfaceServices.choseProduct(currentBill);

                    AppDBImpl.getEntity().statistics.printBill(currentBill);

                    InterfaceServices.allProductsSelected(currentBill);

                    InterfaceServices.questionForClosingBill(currentBill);

                    AppDBImpl.getEntity().saveClosedBill(currentBill);
                }

                int keyBill = InterfaceServices.questionCreateBill();

                if (keyBill == JOptionPane.NO_OPTION) {
                    int keyLogOut = InterfaceServices.questionLogOutSalesMan();

                    if (keyLogOut == JOptionPane.YES_OPTION) {

                        autorizedSalesMan = null;

                        AppDBImpl.getEntity().statistics.showInfo(currentTerminal);

                        key = keyBill;
                    }
                } else {
                    key = keyBill;
                }
            }
        }
    }
}