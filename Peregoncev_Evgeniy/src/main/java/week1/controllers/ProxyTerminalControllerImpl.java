package week1.controllers;

import week1.Exeptions.InvalidLoginException;
import week1.interfaces.IAppDb;
import week1.interfaces.ITerminalController;
import week1.models.Bill;
import week1.models.Product;
import week1.models.Salesman;

import java.util.Comparator;
import java.util.List;

/**
 * Created by ENIAC on 24.11.2017.
 */
public class ProxyTerminalControllerImpl implements ITerminalController {

    private ITerminalController terminalController;

    public ProxyTerminalControllerImpl(ITerminalController terminalController) {
        this.terminalController = terminalController;
    }


    @Override
    public boolean login(String login, String pass) throws InvalidLoginException {

        System.out.println("Logg: login attempt");
        terminalController.login(login, pass);
        return false;
    }

    @Override
    public Bill createBill(){
        System.out.println("Logg: create bill attempt");
        return terminalController.createBill();
    }

    @Override
    public IAppDb getDb() {
        return terminalController.getDb();
    }

    @Override
    public List<Bill> getAllBills() {
        return terminalController.getAllBills();
    }

    @Override
    public Bill addProduct(int billId, Product product) {
        System.out.println("Logg: add product attempt");
        return terminalController.addProduct(billId, product);
    }

    @Override
    public Bill closeBill(int id) {
        System.out.println("Logg: close bill attempt");
        return terminalController.closeBill(id);
    }

    @Override
    public Bill findBillById(int billId) {
        System.out.println("Logg: find bill by id attempt");
        return terminalController.findBillById(billId);
    }

    @Override
    public List<Bill> filterForBills(String start, String end, Comparator<Bill> comparator) {
        System.out.println("Logg: filter for bills attempt");
        return terminalController.filterForBills(start, end, comparator);
    }

    @Override
    public List<Product> getAllProducts() {
        return terminalController.getAllProducts();
    }

    @Override
    public Salesman findSalesmanByLogin(String login) {
        System.out.println("Logg: find salesman by login attempt");
        return terminalController.findSalesmanByLogin(login);
    }

    @Override
    public Salesman getTopOfSalesmans() {
        System.out.println("Logg: get top of salesmans attempt");
        return terminalController.getTopOfSalesmans();
    }

    @Override
    public int getCurrentSalesmanIndex() {
        return terminalController.getCurrentSalesmanIndex();
    }

    @Override
    public void setCurrentSalesmanIndex(int i) {
        terminalController.setCurrentSalesmanIndex(i);
    }
}
