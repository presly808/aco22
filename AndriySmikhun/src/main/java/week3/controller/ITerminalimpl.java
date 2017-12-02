package week3.controller;

import week3.appDB.IappDB;
import week3.model.Product;
import week3.appDB.IappDBimpl;
import week3.model.Bill;
import week3.model.Salesman;

public class ITerminalimpl implements ITerminal {


    private IappDB appDB;
    private Salesman user;

    public ITerminalimpl() {
        appDB = new IappDBimpl();
    }

    public ITerminalimpl(IappDB appDB) {
        this.appDB = appDB;
    }

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
        return bill.getProducts().remove(product);
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
