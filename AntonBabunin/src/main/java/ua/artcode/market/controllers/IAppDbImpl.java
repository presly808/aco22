package ua.artcode.market.controllers;

import ua.artcode.market.exclude.exception.*;
import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.models.*;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.models.money.Money;
import ua.artcode.market.utils.Generator;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static ua.artcode.market.models.BillComparator.billComparator;

public class IAppDbImpl implements IAppDb {

    private int billNextId;
    private int productNextId;

    private List<Bill> bills;
//    private List<Employee> employeeList;
    private Map<Product,Integer> products;


    public IAppDbImpl() {
        this.bills = new ArrayList<>();
        this.products = Generator.randomProducts(0);
    }

//    public List<Employee> getEmployeeList() {
//        return employeeList;
//    }



    @Override
    public Map<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public List<Bill> getBills() {
        return bills;
    }

    @Override
    public List<Employee> getEmployee() {
        return null;
    }

    @Override
    public Bill findBillById(int id) throws BillNotFoundException {
        try {
            Bill bill = this.bills.stream().
                    filter(billE -> billE.getId() == id).findFirst().get();
            return bill;
        } catch (NoSuchElementException e) {
            throw new BillNotFoundException();
        }
    }

    @Override
    public Product findProductById(int id) throws ProductNotFoundException {
        for (Product product : products.keySet()) {
            if (product.getId() == id) return product;
        }
        throw new ProductNotFoundException();
    }

    @Override
    public Bill removeBill(int id) throws BillNotFoundException {
        Bill bill = findBillById(id);
        bills.remove(bill);
        return bill;
    }

    @Override
    public Product removeProduct(int id) throws ProductNotFoundException {
        Product product = findProductById(id);
        products.replace(product, products.get(product) - 1);
        return product;
    }

    @Override
    public Bill saveBill(Bill bill) throws IllegalArgumentException {
        if (bill == null) throw new IllegalArgumentException();
            bill.setId(++billNextId);
            bills.add(bill);
            return bill;
    }

    @Override
    public Product saveProduct(Product product) throws IllegalArgumentException {
        if (product == null) throw new IllegalArgumentException();
        product.setId(++productNextId);
        products.putIfAbsent(product, 0);
        return product;
    }

    @Override
    public Bill update(Bill bill) throws IllegalArgumentException, BillNotFoundException {
        if (bill == null) throw new IllegalArgumentException();
        int index = bills.indexOf(bill);

        if (index == -1) throw new BillNotFoundException();

        return bills.set(index, bill);
    }

    @Override
    public Employee createSalesman(String fullName, String login,
                                   String password) {
        return new Salesman();
    }

    @Override
    public List<Bill> filter(Employee salesman, Product product,
                             LocalDateTime startDate, LocalDateTime endDate,
                             Comparator<Bill> billComparator) {
        if (getBills() == null) return null;

        List<Bill> filtered = new ArrayList<>();
        filtered.addAll(getBills());

        if (salesman != null)
            filtered = addToListBySeller(filtered, salesman);

        if (product != null)
            filtered = addProductToList(filtered, product);

        if (startDate != null)
            filtered = addToListByStartDate(filtered, startDate);

        if (endDate != null)
            filtered = addToListByEndDate(filtered, endDate);

        if (billComparator != null) {

            filtered.sort(billComparator.reversed());

        }
        return filtered;
    }

    private List<Bill> addProductToList(List<Bill> listBills,
                                        Product product) {
        List<Bill> list = new ArrayList<>();

        if (product == null) throw new NullArgumentException();

        if (listBills == null) throw new NullArgumentException();

        for (Bill bill : listBills) {
            if ((bill.getProductsMap().containsKey(product))) {
                list.add(bill);
            }
        }
        return list;
    }

    private List<Bill> addToListBySeller (List<Bill> listBills,
                                          Employee salesman) {
        List<Bill> list = new ArrayList<>();

        if (salesman == null) return listBills;

        if (listBills != null) {
            for (Bill bill : listBills) {
                if ((salesman.equals(bill.getSalesman()))) {
                    list.add(bill);
                }
            }
        }
        return list;
    }

    private List<Bill> addToListByStartDate(List<Bill> listBills,
                                            LocalDateTime date) {
        if (listBills == null) throw new NullArgumentException();
        if (date == null) return listBills;
        return listBills.stream().
                filter(bill -> date.compareTo(bill.getOpenTime()) >= 0).
                collect(Collectors.toList());
    }

    private List<Bill> addToListByEndDate(List<Bill> listBills,
                                          LocalDateTime date) {
        if (listBills == null ) throw new NullArgumentException();
        if (date == null) return listBills;
        return listBills.stream().
                filter(bill -> date.compareTo(bill.getCloseTime()) <= 0).
                collect(Collectors.toList());
    }

    @Override
    public Money aggrAmtPrice(Salesman salesman, LocalDateTime startDate,
                              LocalDateTime endDate) {
        if (bills == null) throw new NullArgumentException();
        return aggrAmPrice(filter(salesman,null, startDate, endDate, null));
    }

    private Money aggrAmPrice(List<Bill> filteredList){
        final Money summ = new Money(0,0);
        if (filteredList == null) throw new NullArgumentException();
        if(filteredList.isEmpty()) return summ;

        filteredList.stream().map(bill -> summ.doSum(bill.getAmountPrice())).
                close();

        return summ;


    }

    @Override
    public Money averageAmountPrice(Salesman salesman, LocalDateTime startDate,
                                    LocalDateTime endDate) {
        List<Bill> filteredList = filter(salesman,null, startDate, endDate, null);
        if (filteredList == null || filteredList.isEmpty())
            return new Money(0,0);
        return  aggrAmPrice(filteredList).div(filteredList.size());
    }

    @Override
    public Bill minAmountPrice(Salesman salesman, LocalDateTime startDate,
                               LocalDateTime endDate) {

        List<Bill> filtered = filter(salesman, null, startDate, endDate,
                billComparator);
        if (filtered == null || filtered.isEmpty())
            return null;
        return filtered.get(0);
    }

    @Override
    public Bill maxAmountPrice(Salesman salesman, LocalDateTime startDate,
                               LocalDateTime endDate) {
        List<Bill> filtered = filter(salesman, null, startDate, endDate,
                billComparator);
        if (filtered == null || filtered.isEmpty())
            return null;
        return filtered.get(filtered.size() - 1);
    }
}
