package ua.artcode.market.view;

import ua.artcode.market.controllers.AppDBImpl;
import ua.artcode.market.controllers.BillController;
import ua.artcode.market.controllers.TerminalController;
import ua.artcode.market.factory.MarketFactory;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.SalesMan;
import ua.artcode.market.model.Terminal;
import ua.artcode.market.proxy.MarketProxy;

import javax.swing.*;
import java.io.IOException;

public class MiniMarket {

    public AppDBImpl currentAppDBImpl;

    public MiniMarket(AppDBImpl newAppDBImpl) {
        this.currentAppDBImpl = newAppDBImpl;
    }

    public static void main(String[] args) throws IOException {

        MarketProxy marketProxy = MarketFactory.createProxy();

        marketProxy.getMiniMarket().startMarket();
    }

    public void startMarket() throws IOException {

        JOptionPane.showMessageDialog(null, "Good afternoon!\n" + "Shop starts work");

        this.currentAppDBImpl.generator.initSalesMans(2);

        SalesMan autorizedSalesMan = InterfaceServices.autorizationSalesMan(this.currentAppDBImpl);

        if (autorizedSalesMan != null) {

            Terminal currentTerminal = this.currentAppDBImpl.createTerminal();
            currentTerminal.setAutorizedSalesMan(autorizedSalesMan);

            String stringCountProducts = JOptionPane.showInputDialog("Enter the number of products in the store", 0);
            int countProducts = Integer.parseInt((stringCountProducts == null ? "0" : stringCountProducts));

            this.currentAppDBImpl.generator.initProductsPrice(countProducts);

            int key = InterfaceServices.questionCreateBill();

            while (key == JOptionPane.YES_OPTION) {

                Bill currentBill = currentAppDBImpl.createBill(currentTerminal);

                while (!currentBill.closed) {

                    InterfaceServices.choseProduct(currentBill);

                    currentAppDBImpl.statistics.printBill(currentBill);

                    InterfaceServices.allProductsSelected(currentBill);

                    InterfaceServices.questionForClosingBill(currentBill);

                    this.currentAppDBImpl.saveClosedBill(currentBill);

                }

                int keyBill = InterfaceServices.questionCreateBill();

                if (keyBill == JOptionPane.NO_OPTION) {
                    int keyLogOut = InterfaceServices.questionLogOutSalesMan();

                    if (keyLogOut == JOptionPane.YES_OPTION) {

                        autorizedSalesMan = null;

                        this.currentAppDBImpl.statistics.showInfo(currentTerminal);

                        key = keyBill;
                    }
                } else {
                    key = keyBill;
                }
            }
        }
    }
}