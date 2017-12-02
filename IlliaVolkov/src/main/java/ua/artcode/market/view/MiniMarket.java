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

public class MiniMarket {

    public AppDBImpl currentAppDBImpl;

    public MiniMarket(AppDBImpl newAppDBImpl) {
        this.currentAppDBImpl = newAppDBImpl;
    }

    public static void main(String[] args) {

        MarketProxy marketProxy = MarketFactory.createProxy();

        marketProxy.getMiniMarket().startMarket();
    }

    public void startMarket() {

        JOptionPane.showMessageDialog(null, "Good afternoon!\n" + "Shop starts work");

        this.currentAppDBImpl.generator.initSalesMans(2);

        SalesMan autorizedSalesMan = autorizationSalesMan();

        if (autorizedSalesMan != null) {

            Terminal currentTerminal = this.currentAppDBImpl.createTerminal();
            currentTerminal.setAutorizedSalesMan(autorizedSalesMan);

            String stringCountProducts = JOptionPane.showInputDialog("Enter the number of products in the store", 0);
            int countProducts = Integer.parseInt((stringCountProducts == null ? "0" : stringCountProducts));

            this.currentAppDBImpl.generator.initProductsPrice(countProducts);

            int key = InterfaceServices.questionCreateBill();

            while (key == JOptionPane.YES_OPTION) {

                Bill currentBill = BillController.createBill(currentTerminal);

                while (!currentBill.closed) {

                    currentTerminal.billController.choseProduct(currentBill);

                    BillController.printBill(currentBill);

                    currentTerminal.billController.allProductsSelected(currentBill);

                    currentTerminal.billController.questionForClosingBill(currentBill);

                    this.currentAppDBImpl.saveClosedBill(currentBill);

                }

                int keyBill = InterfaceServices.questionCreateBill();

                if (keyBill == JOptionPane.NO_OPTION) {
                    int keyLogOut = InterfaceServices.questionLogOutSalesMan();

                    if (keyLogOut == JOptionPane.YES_OPTION) {

                        autorizedSalesMan = null;

                        this.currentAppDBImpl.terminalController.showInfo(currentTerminal);

                        key = keyBill;
                    }
                } else {
                    key = keyBill;
                }
            }
        }
    }

    public SalesMan autorizationSalesMan() {

        int key = JOptionPane.YES_OPTION;

        while (key == JOptionPane.YES_OPTION) {

            String message = "Enter the SalesMan <LOGIN> ";
            String loginSalesMan = JOptionPane.showInputDialog(message);

            message = "Enter the SalesMan <PASSWORD> ";
            String passSalesMan = JOptionPane.showInputDialog(message);

            SalesMan currentSalesMan = this.currentAppDBImpl.findSalesMan(loginSalesMan, passSalesMan);

            if (currentSalesMan == null) {
                message = "Do you want to try again?";
                String title = "Autorization of the SalesMan";
                key = JOptionPane.showConfirmDialog( null, message, title, JOptionPane.YES_NO_OPTION);

                if (key == JOptionPane.NO_OPTION) {
                    return null;
                }
            }
            else {
                return currentSalesMan;
            }
        }

        return null;
    }
}