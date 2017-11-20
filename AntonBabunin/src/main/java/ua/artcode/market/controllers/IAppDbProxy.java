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
    public List<Bill> getAllBills() {
        return target.getAllBills();
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
        return target.removeBill(id);
    }

    @Override
    public Product removeProduct(int id) {
        return target.removeProduct(id);
    }

    @Override
    public Bill saveBill(Bill bill) {
        return target.saveBill(bill);
    }

    @Override
    public Product saveProduct(Product product) {
        return target.saveProduct(product);
    }

    @Override
    public Bill update(Bill bill) {
        return target.update(bill);
    }

    @Override
    public Salesman createSalesman(String fullName, String login,
                                   String password) throws IOException {
        boolean result = false;
        Salesman salesman = iLogging.createSalesman(fullName, login, password);
        if (salesman != null) {
            result = true;
        }

        write(String.format("Salesman was created: FullName %s login %s " +
                        "and password %s," + "result %s\r\n",
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
    public Salesman logout(Salesman salesman) {
        return iLogging.logout(salesman);
    }


}
