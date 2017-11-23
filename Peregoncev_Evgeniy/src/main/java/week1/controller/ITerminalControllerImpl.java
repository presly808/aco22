package week1.controller;

import week1.model.Bill;
import week1.model.Product;
import week1.model.Salesman;

import java.util.List;

/**
 * Created by ENIAC on 10.11.2017.
 */
public class ITerminalControllerImpl implements ITerminalController {

    private IAppDb iAppDb;

    public ITerminalControllerImpl(IAppDb iAppDb) {
        this.iAppDb = iAppDb;
    }

    private int currentSallerIndex = -1;

    public int getCurrentSallerIndex() {
        return currentSallerIndex;
    }

    public void setCurrentSallerIndex(int currentSallerIndex) {
        this.currentSallerIndex = currentSallerIndex;
    }

    //Methods

    @Override
    public void login(String login, String pass) {
        if (login == null || login.isEmpty() || pass == null || pass.isEmpty()) {
            System.out.println("write true login/pass");
        } else if (iAppDb == null) {
            System.out.println("wrong salesman database");
        }

        for (Salesman salesman : iAppDb.getAllSalesMans()) {

            if ((salesman.getLogin().equals(login)) && (salesman.getPass().equals(pass))) {
                setCurrentSallerIndex(salesman.getId());
                System.out.println("Hello " + salesman.getName());
            }
        }
        if (currentSallerIndex == -1) System.out.println("wrong login/pass");
    }

    @Override
    public Bill createBill() {

        Bill bill = new Bill();

        bill.setSalesman(iAppDb.getAllSalesMans().get(getCurrentSallerIndex()));

        Bill billWithId = iAppDb.saveBill(bill);

        return billWithId;
    }

    @Override
    public Bill findBillById(int billId) {

        return iAppDb.findByBillId(billId);
    }

    @Override
    public Salesman findSalesmanByLogin(String login) {

        return iAppDb.findSalesmanByLogin(login);
    }

    @Override
    public Bill addProduct(int billId, Product product) {
        Bill bill = iAppDb.findByBillId(billId);

        if (bill == null) {
            System.out.println("no bill found");
            return null;
        }

        bill.getProductList().add(product);
        bill.setAmountPrice(bill.getAmountPrice() + product.getPrice());

        iAppDb.update(bill);

        return bill;
    }

    @Override
    public List<Bill> getAllBills() {
        return iAppDb.getAllBills();
    }

    @Override
    public Bill closeBill(int id) {
        Bill bill = iAppDb.findByBillId(id);
        bill.getTime().setCloseTime(bill.getTime().printTime());
        bill.setClosed(true);

        bill.getSalesman().setCountSoldProduct(bill.getSalesman().getCountSoldProduct() + (bill.getProductList().size()));
        iAppDb.update(bill);

        return bill;
    }

    @Override
    public List<Product> getAllProducts() {
        return iAppDb.getAllProducts();
    }


}
