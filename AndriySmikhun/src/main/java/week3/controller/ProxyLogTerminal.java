package week3.controller;

import week3.model.Product;
import week3.model.Bill;
import week3.model.Salesman;

public class ProxyLogTerminal implements ITerminal {

    private ITerminal realterminal;

    public ProxyLogTerminal() {
    }

    public ProxyLogTerminal(ITerminal realterminal) {
        this.realterminal = realterminal;
    }

    private Log loger = Log.getLog();

    @Override
    public boolean login(String login, String password) {
        loger.event("Log in system user " + login);

        if (realterminal.login(login, password)) {
            loger.event("User in System");
            return true;
        }
        loger.error("User not logged");
        return false;
    }

    //  @Override
    // public boolean logOut() {
    //    return false;
    //}

    @Override
    public boolean addProduct(int id, Product product) {
        loger.event("Add product" + product.getName() + "in bill" + id);
        if (realterminal.addProduct(id, product)) {
            loger.event("Product added");
            return true;
        }
        loger.error("Product didn't add");
        return false;
    }

    @Override
    public boolean deleteProduct(int idBill, Product product) {
        loger.event("Delete product" + product + " from bill" + idBill);
        if (realterminal.addProduct(idBill, product)) {
            loger.event("Product was deleted");
        }
        loger.error("Product did'nt delete");
        return false;
    }

   /* @Override
    public Bill maxBill() {
        loger.event("Find Max Bill");
        Bill bill = realterminal.maxBill();
        if (bill != null){
            loger.event("Bill was find");
            return bill;
        }
        loger.error("Bill did'nt find");
        return null;
    }

    @Override
    public Bill minBill() {
        loger.event("Find Min Bill");
        Bill bill = realterminal.minBill();
        if (bill != null){
            loger.event("Bill was find");
            return bill;
        }
        loger.error("Bill did'nt find");
        return null;
    }*/

    @Override
    public Bill[] filterBill() {
        loger.event("Filter by bill");
        Bill[] bills = realterminal.filterBill();
        if (bills != null) {
            loger.event("Fillter finded bills");
            return bills;
        }
        loger.error("Filter did'nt work");
        return null;
    }

    @Override
    public boolean closeBill(Bill bill) {
        loger.event("Close bill");
        if (realterminal.closeBill(bill)) {
            loger.event("Bill was closed");
            return true;
        }
        loger.error("Bill did'nt close");
        return false;
    }

    @Override
    public boolean createSalesMan(String login, String password, String fullName) {
        loger.event("Create user in system");

        if (realterminal.createSalesMan(login,password,fullName)) {
            loger.event("User was created");
            return true;

        }
        loger.error("User did'nt create");
        return false;
    }

    @Override
    public Salesman getTopSalesman() {
        loger.event("Search Top Salesman By average");
        Salesman salesman = realterminal.getTopSalesman();
        if (salesman != null) {
            loger.event("Salesman was fount");
            return salesman;
        }
        loger.error("Salesman did'nt find");
        return null;
    }
}
