package week3.controller;

import week1.model.Product;
import week3.AppDB.IappDBimpl;
import week3.model.Bill;
import week3.model.Salesman;

public class ITerminalimpl implements ITerminal{
    private IappDBimpl  db = new IappDBimpl();
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
        return false;
    }

    @Override
    public boolean deleteProduct(int idBill, Product product) {
        return false;
    }

    @Override
    public Bill maxBill() {
        return null;
    }

    @Override
    public Bill minBill() {
        return null;
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
