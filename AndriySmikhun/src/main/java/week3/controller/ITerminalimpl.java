package week3.controller;

import week3.model.Product;
import week3.appDB.IappDBimpl;
import week3.model.Bill;
import week3.model.Salesman;

public class ITerminalimpl implements ITerminal {
    private IappDBimpl appDB = new IappDBimpl();
    private Salesman user;

    @Override
    public boolean login(String login, String password) {
        return false;
    }

    //@Override
    //public boolean logOut(Salesman salesman) {
    //  return false;
    //}

    @Override
    public boolean addProduct(int id, Product product) {
        Bill bill = appDB.findeBillByID(id);
        if (bill != null) {
            bill.getProducts().add(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int idBill, Product product) {
        Bill bill = appDB.findeBillByID(idBill);
        if (bill.getProducts().remove(product)){
            return true;
        }
        return false;
    }


    @Override
    public Bill[] filterBill() {
        return new Bill[0];
    }

    @Override
    public boolean closeBill(Bill bill) {
        return false;
    }

    @Override
    public boolean createSalesMan() {
        return false;
    }

    @Override
    public Salesman getTopSalesman() {
        return null;
    }
}
