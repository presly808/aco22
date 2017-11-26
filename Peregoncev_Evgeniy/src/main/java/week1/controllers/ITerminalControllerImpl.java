package week1.controllers;

import week1.comparators.SalesmanSoldProductComparator;
import week1.interfaces.IAppDb;
import week1.interfaces.ITerminalController;
import week1.models.Bill;
import week1.models.Product;
import week1.models.Salesman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ENIAC on 10.11.2017.
 */
public class ITerminalControllerImpl implements ITerminalController {

    private IAppDb iAppDb;

    private int currentSalesmanIndex = -1;

    public ITerminalControllerImpl(IAppDb iAppDb) {
        this.iAppDb = iAppDb;
    }


    public int getCurrentSalesmanIndex() {
        return currentSalesmanIndex;
    }

    public void setCurrentSalesmanIndex(int currentSalesmanIndex) {
        this.currentSalesmanIndex = currentSalesmanIndex;
    }


    //Methods


    @Override
    public IAppDb getDb() {
        return iAppDb;
    }

    @Override
    public List<Bill> getAllBills() {
        return iAppDb.getAllBills();
    }

    @Override
    public List<Product> getAllProducts() {
        return iAppDb.getAllProducts();
    }

    @Override
    public void login(String login, String pass) {
        if (login == null || login.isEmpty() || pass == null || pass.isEmpty()) {
            System.out.println("write true login/pass");
        } else if (iAppDb == null) {
            System.out.println("wrong salesman database");
        }

        for (Salesman salesman : iAppDb.getAllSalesMans()) {

            if ((salesman.getLogin().equals(login)) && (salesman.getPass().equals(pass))) {
                setCurrentSalesmanIndex(salesman.getId());
                System.out.println("Hello " + salesman.getName());
            }
        }
        if (currentSalesmanIndex == -1) {
            System.out.println("wrong login/pass");
            Runtime.getRuntime().exit(0);
        }

    }

    @Override
    public Bill createBill() {

        Bill bill = new Bill();

        bill.setSalesman(iAppDb.getAllSalesMans().get(getCurrentSalesmanIndex()));

        Bill billWithId = iAppDb.saveBill(bill);

        return billWithId;
    }

    @Override
    public Bill addProduct(int billId, Product product) {
        Bill bill = iAppDb.findByBillId(billId);

        if (bill == null) {
            System.out.println("no bill found");
            return null;

        } else if (bill.isClosed()) {
            System.out.println("bill is closed. cant add product to closed bill.");
            return null;

        } else {

            bill.getProductList().add(product);
            bill.setAmountPrice(bill.getAmountPrice() + product.getPrice());

            iAppDb.update(bill);

            System.out.println("product " + product.getName()
                    + " was added to bill with id " + product.getId());
            return bill;
        }
    }

    @Override
    public Bill closeBill(int id) {

        if (id < getAllBills().size()) {
            Bill bill = iAppDb.findByBillId(id);

            if (bill.isClosed()) {
                System.out.println("bill is already closed");
                return null;
            }

            bill.getTime().setCloseTime(bill.getTime().printTime());
            bill.setClosed(true);
            bill.getSalesman().setCountSoldProduct
                    (bill.getSalesman().getCountSoldProduct() + (bill.getProductList().size()));

            iAppDb.update(bill);

            System.out.println("bill " + id + " now is closed");
            return bill;
        } else {
            System.out.println("There no bill with this id");
        }
        return null;
    }

    @Override
    public Bill findBillById(int billId) {

        Bill bill = iAppDb.findByBillId(billId);

        if (!bill.isClosed()) {

            System.out.println("bill is NOT closed. cant show non closed bill.");
            return null;

        } else {

            return bill;
        }
    }

    @Override
    public Salesman findSalesmanByLogin(String login) {

        Salesman salesman = iAppDb.findSalesmanByLogin(login);
        if (salesman == null) {
            System.out.println("there no one salesman with login " + login);
            return null;
        } else {
            return salesman;
        }
    }

    @Override
    public Salesman getTopOfSalesmans() {
        int sumOfAllSoldProduct = 0;
        for (Salesman salesman : iAppDb.getAllSalesMans()) {
            sumOfAllSoldProduct += salesman.getCountSoldProduct();
        }
        if (sumOfAllSoldProduct == 0) {
            System.out.println("Still no one sold anything.");
            return null;
        }

        iAppDb.getAllSalesMans().sort(new SalesmanSoldProductComparator());
        return iAppDb.getAllSalesMans().get(iAppDb.getAllSalesMans().size() - 1);
    }

    @Override
    public List<Bill> filterForBills(String start, String end, Comparator<Bill> comparator) {

        List<Bill> billList = new ArrayList<>();

        for (Bill bill : iAppDb.getAllBills()) {
            if (bill.getTime().getCloseTime().compareTo(start) > 0 &&
                    bill.getTime().getCloseTime().compareTo(end) < 0) {
                billList.add(bill);
            }
        }
        billList.sort(comparator);
        return billList;
    }

}
