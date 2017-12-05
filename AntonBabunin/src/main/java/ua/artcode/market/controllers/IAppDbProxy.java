package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.interfaces.ILogging;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.models.money.Money;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class IAppDbProxy implements IAppDb, ILogging{

    private IAppDb iAppDb;
    private ILogging iLogging;


    public IAppDbProxy(IAppDb iAppDb) throws IOException {
        this.iAppDb = iAppDb;
        this.iLogging = ILoggingImpl.getInstance();
    }

    @Override
    public void write(String messege) throws IOException {
        iLogging.write(messege);
    }

    @Override
    public Map<Product, Integer> getProducts() {
        return iAppDb.getProducts();
    }

    @Override
    public List<Bill> getBills() {
        return iAppDb.getBills();
    }

    @Override
    public List<Employee> getEmployee() {
        return iAppDb.getEmployee();
    }

    @Override
    public List<Employee> getAllSalesmans() {
        return iLogging.getAllSalesmans();
    }

    @Override
    public Bill findBillById(int id) {
        return iAppDb.findBillById(id);
    }

    @Override
    public Product findProductById(int id) {
        return iAppDb.findProductById(id);
    }

    @Override
    public Employee findSalesmanByLogin(String login) {
        return iLogging.findSalesmanByLogin(login);
    }

    @Override
    public List<Bill> filter(Employee salesman, Product product,
                            LocalDateTime startDate, LocalDateTime endDate,
                            Comparator<Bill> billComparator) {
        return iAppDb.filter(salesman, product, startDate, endDate,
                billComparator);
    }

    @Override
    public Money aggrAmtPrice(Salesman salesman, LocalDateTime startDate,
                              LocalDateTime endDate) {
        return iAppDb.aggrAmtPrice(salesman, startDate,endDate);
    }

    @Override
    public Money averageAmountPrice(Salesman salesman, LocalDateTime startDate,
                                    LocalDateTime endDate) {
        return iAppDb.averageAmountPrice(salesman, startDate, endDate);
    }

    @Override
    public Bill minAmountPrice(Salesman salesman, LocalDateTime startDate,
                               LocalDateTime endDate) {
        return iAppDb.minAmountPrice(salesman, startDate, endDate);
    }

    @Override
    public Bill maxAmountPrice(Salesman salesman, LocalDateTime startDate,
                               LocalDateTime endDate) {
        return iAppDb.maxAmountPrice(salesman, startDate, endDate);
    }

    @Override
    public Bill removeBill(int id) throws IOException {
        Bill bill = iAppDb.removeBill(id);
        String messege = null;
        if (bill == null) {
            messege = String.format("Bill %s not found \r\n", bill);
            iLogging.write(messege);
            return null;
        }
        messege = String.format("Bill %s removed \r\n", bill);
        iLogging.write(messege);
        return bill;
    }

    @Override
    public Product removeProduct(int id) throws IOException {
        Product product1 = iAppDb.removeProduct(id);
        String messege = null;
        if (product1 == null) {
            messege = String.format("Product %s wasn't removed \r\n", product1);
            iLogging.write(messege);
            return null;
        }
        messege = String.format("Product %s saved \r\n", product1);
        iLogging.write(messege);
        return product1;
    }

    @Override
    public Bill saveBill(Bill bill) throws IOException {
        Bill bill1 = iAppDb.saveBill(bill);
        String messege = null;
        if (bill1 == null) {
            messege = String.format("Bill %s wasn't saved \r\n", bill1);
            iLogging.write(messege);
            return null;
        }
        messege = String.format("Bill %s saved \r\n", bill1);
        iLogging.write(messege);
        return bill1;
    }

    @Override
    public Product saveProduct(Product product) throws IOException {
        Product product1 = iAppDb.saveProduct(product);
        String messege = null;
        if (product1 == null) {
            messege = String.format("Product %s wasn't saved \r\n", product1);
            iLogging.write(messege);
            return null;
        }
        messege = String.format("Product %s saved \r\n", product1);
        iLogging.write(messege);
        return product1;
    }

    @Override
    public final Bill update(Bill bill) throws IOException {
        Bill found = iAppDb.update(bill);
        String messege = null;
        if (bill.getCloseTime() != null) {
            messege = String.format("Bill %s is closed ad it can't be " +
                            "updated \r\n", bill);
            iLogging.write(messege);
            return null;
        }
        if (found == null){
            messege = String.format("Bill %s not found \r\n", bill);
            iLogging.write(messege);
            return found;
        }
        messege = String.format("Bill %s was updated \r\n", bill);
        iLogging.write(messege);
        return found;
    }

    @Override
    public Employee createSalesman(String fullName, String login,
                                   String password) throws IOException {
        boolean result = false;
        Employee salesman = iLogging.createSalesman(fullName, login, password);
        if (salesman != null) {
            result = true;
        }
        iLogging.write(String.format("Employee was created: " +
                        "FullName %s login %s and password %s, result %s\r\n",
                fullName, login, password, result));

        return salesman;
    }

    @Override
    public Employee login(String login, String password) throws IOException {

        boolean result = false;
        Employee salesman = iLogging.login(login, password);
        if (salesman != null) {
            result = true;
        }
        iLogging.write(String.format("Employee try connect with login %s " +
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
        iLogging.write(String.format("Employee was logout with login %s " +
                        "and password %s," + "result %s\r\n",
                salesman1.getLogin(), salesman1.getPassword(), result));
        return salesman1;
    }


}
