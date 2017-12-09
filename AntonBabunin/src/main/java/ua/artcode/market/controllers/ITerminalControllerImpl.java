package ua.artcode.market.controllers;

import ua.artcode.market.exclude.exception.*;
import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.interfaces.ITerminalController;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.models.money.Money;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ITerminalControllerImpl implements ITerminalController {

//    private static int terminalID = 0;
    private IAppDb iAppDb;

    public ITerminalControllerImpl(IAppDb iAppDb) {
        this.iAppDb = iAppDb;
    }

    public IAppDb getIAppDb() {
        return iAppDb;
    }

    @Override
    public Employee createSalesman(String fullName, String login,
                                   String password) throws IOException {
        return iAppDb.createSalesman(fullName, login, password);
    }

//    @Override
//    public Employee login(String login, String password) throws IOException {
//        return iAppDb.login(login, password);
//    }

//    @Override
    public Employee logout(Salesman salesman) throws IOException {
//        return iAppDb.logout(salesman);
        return null;
    }

    @Override
    public Employee findSalesmanByLogin(String login) {
//        return iAppDb.findSalesmanByLogin(login);
        return null;
    }

    @Override
    public Bill createBill() throws IOException {
        Bill bill = new Bill();
        bill.setOpenTime(LocalDateTime.now());
        return iAppDb.saveBill(bill);
    }

    @Override
    public Bill addProduct(int billId, Product product) throws IOException, BillNotFoundException {
        Bill bill = iAppDb.findBillById(billId);

        if (bill == null || product == null) return null;
        if (!bill.getProductsMap().containsKey(product)) {
            bill.getProductsMap().putIfAbsent(product, 1);
            bill.setAmountPrice(calculateAmountPrice(bill));
            iAppDb.getProducts().replace(product,
                    iAppDb.getProducts().get(product) - 1);
            return bill;
        }

        bill.getProductsMap().replace(product,
                bill.getProductsMap().get(product) + 1);
        bill.setAmountPrice(calculateAmountPrice(bill));
        iAppDb.getProducts().replace(product,
                iAppDb.getProducts().get(product) - 1);
        iAppDb.update(bill);

        return bill;
    }

    @Override
    public List<Bill> getBills() {
        return iAppDb.getBills();
    }

    @Override
    public Money calculateAmountPrice(Bill bill) {
        Money amountPrice = new Money(0,0);
        if (bill == null || bill.getProductsMap() == null ||
                bill.getProductsMap().isEmpty()) return amountPrice;
        List<Money> monies = bill.getProductsMap().keySet().stream().
                map(product -> product.getPrice().
                        multiply(bill.getProductsMap().get(product))).
                collect(Collectors.toList());

        return calculateAmountPrice(monies);

//          Code like that is easier
//        for (Map.Entry<Product, Integer> pair :
//                bill.getProductsMap().entrySet()) {
//            amountPrice = amountPrice.doSum(pair.getKey().getPrice().
//                    multiply(pair.getValue()));
//        }
    }

    private Money calculateAmountPrice(List<Money> monies) {
        final Money amountPrice = new Money(0,0);
        monies.stream().
                peek(money -> amountPrice.doSum(money));
        return amountPrice;
    }

    @Override
    public String prinBill(Bill bill) {
        return bill.toString();
    }

    @Override
    public Bill closeBill(int id) throws IOException, BillNotFoundException {
        Bill bill = iAppDb.findBillById(id);
        bill.setCloseTime(LocalDateTime.now());
        iAppDb.update(bill);
        return bill;
    }




}
