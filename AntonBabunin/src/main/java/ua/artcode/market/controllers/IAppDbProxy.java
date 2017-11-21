package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.interfaces.ILogging;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.io.*;
import java.util.List;
import java.util.Map;

public class IAppDbProxy implements IAppDb, ILogging{

    private IAppDb target;
    private ILogging iLogging;


    public IAppDbProxy(IAppDb target) {
        this.target = target;
        this.iLogging = ILoggingImpl.getGetInstance();
    }

    @Override
    public void write(String messege) throws IOException {
        iLogging.write(messege);
    }

    @Override
    public Map<Product, Integer> getProducts() {
        return target.getProducts();
    }

    @Override
    public List<Bill> getBills() {
        return target.getBills();
    }

    @Override
    public Map<Product, Integer> getAllProducts() {
        return target.getAllProducts();
    }

    @Override
    public List<Salesman> getAllSalesmans() {
        return iLogging.getAllSalesmans();
    }

    @Override
    public Bill findBillById(int id) {
        return target.findBillById(id);
    }

    @Override
    public Product findProductById(int id) {
        return target.findProductById(id);
    }

    @Override
    public Salesman findSalesmanByLogin(String login) {
        return iLogging.findSalesmanByLogin(login);
    }

    @Override
    public Bill removeBill(int id) {
        Bill bill = target.removeBill(id);
        String messege = null;
        if (bill == null) {
            messege = String.format("Bill %s not found", bill);
            return null;
        }
        messege = String.format("Bill %s removed", bill);

        return bill;
    }

    @Override
    public Product removeProduct(int id) {
        Product product1 = target.removeProduct(id);
        String messege = null;
        if (product1 == null) {
            messege = String.format("Product %s wasn't removed", product1);
            return null;
        }
        messege = String.format("Product %s saved", product1);

        return product1;

    }

    @Override
    public Bill saveBill(Bill bill) {
        Bill bill1 = target.saveBill(bill);
        String messege = null;
        if (bill1 == null) {
            messege = String.format("Bill %s wasn't saved", bill1);
            return null;
        }
        messege = String.format("Bill %s saved", bill1);

        return bill1;
    }

    @Override
    public Product saveProduct(Product product) {
        Product product1 = target.saveProduct(product);
        String messege = null;
        if (product1 == null) {
            messege = String.format("Product %s wasn't saved", product1);
            return null;
        }
        messege = String.format("Product %s saved", product1);

        return product1;
    }

    @Override
    public final Bill update(Bill bill) {
        Bill found = target.update(bill);
        String messege = null;
        if (bill.getCloseTime() != null) {
            messege = String.format("Bill %s is closed ad it can't be updated",
                    bill);
            return null;
        }
        if (found == null){
            messege = String.format("Bill %s not founcd", bill);
            return found;
        }

        messege = String.format("Bill %s was updated", bill);
        return found;
    }

    @Override
    public Salesman createSalesman(String fullName, String login,
                                   String password) throws IOException {
        boolean result = false;
        Salesman salesman = iLogging.createSalesman(fullName, login, password);
        if (salesman != null) {
            result = true;
        }

        iLogging.write(String.format("Salesman was created: " +
                        "FullName %s login %s and password %s, result %s\r\n",
                fullName, login, password, result));

        return salesman;
    }

    @Override
    public Salesman login(String login, String password) throws IOException {

        boolean result = false;
        Salesman salesman = iLogging.login(login, password);
        if (salesman != null) {
            result = true;
        }
        iLogging.write(String.format("Salesman try connect with login %s " +
                        "and password %s," + "result %s\r\n",
                        login, password, result));

        return salesman;
    }

    @Override
    public Salesman logout(Salesman salesman) throws IOException {
        boolean result = false;
        Salesman salesman1 = iLogging.logout(salesman);
        if (salesman1 != null) {
            result = true;
        }
        iLogging.write(String.format("Salesman was logout with login %s " +
                        "and password %s," + "result %s\r\n",
                salesman1.getLogin(), salesman1.getPassword(), result));


        return salesman1;
    }


}
