package week1.ProxyTerminalController;

import week1.AbstractFactory.ITerminalControllerFactory;
import week1.interfaces.ITerminalController;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Salesman;

import java.util.Comparator;
import java.util.List;

/**
 * Created by ENIAC on 24.11.2017.
 */
public class ProxyTerminalControllerImpl implements ITerminalController {

    private ITerminalController terminalController;

    private static ProxyTerminalControllerImpl instance = new ProxyTerminalControllerImpl();

    private ProxyTerminalControllerImpl() {
        this.terminalController = ITerminalControllerFactory.create();
    }


    public static ProxyTerminalControllerImpl getInstance() {
        return instance;
    }

    @Override
    public boolean login(String login, String pass) {

        System.out.println("logger message");
        return terminalController.login(login, pass);
    }

    @Override
    public Bill createBill() {
        return terminalController.createBill();
    }

    @Override
    public List<Bill> getAllBills() {
        return terminalController.getAllBills();
    }

    @Override
    public Bill addProduct(int billId, Product product) {
        return terminalController.addProduct(billId, product);
    }

    @Override
    public Bill closeBill(int id) {
        return terminalController.closeBill(id);
    }

    @Override
    public Bill findBillById(int billId) {
        return terminalController.findBillById(billId);
    }

    @Override
    public List<Bill> filterForBills(String start, String end, Comparator<Bill> comparator) {
        return terminalController.filterForBills(start, end, comparator);
    }

    @Override
    public List<Product> getAllProducts() {
        return terminalController.getAllProducts();
    }

    @Override
    public Salesman findSalesmanByLogin(String login) {
        return terminalController.findSalesmanByLogin(login);
    }

    @Override
    public Salesman getTopOfSalesmans() {
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
