package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.models.*;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.utils.Generator;

import java.time.LocalDateTime;
import java.util.*;

import static ua.artcode.market.models.BillComparator.billComparator;

public class IAppDbImpl implements IAppDb {

    private int billNextId;
    private int productNextId;

    private List<Bill> bills;
    private List<Employee> employeeList;
    private Map<Product,Integer> products;


    public IAppDbImpl() {
        this.bills = new ArrayList<>();
        this.products = Generator.randomProducts(0);
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

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
    public Bill findBillById(int id) {
        for (Bill bill : bills) {
            if (bill.getId() == id) return bill;
        }
        return null;
    }

    @Override
    public Product findProductById(int id) {
        for (Product product : products.keySet()) {
            if (product.getId() == id) return product;
        }
        return null;
    }

    @Override
    public Bill removeBill(int id) {
        Bill bill = findBillById(id);

        if (bill == null) return null;

        bills.remove(bill);

        return bill;
    }

    @Override
    public Product removeProduct(int id) {
        Product product = findProductById(id);
        if (product != null) {
            products.replace(product, products.get(product) - 1);
        }
        return product;
    }

    @Override
    public Bill saveBill(Bill bill) {
        if (bill != null) {
            bill.setId(++billNextId);
            bills.add(bill);
            return bill;
        } return null;
    }

    @Override
    public Product saveProduct(Product product) {
        product.setId(++productNextId);
        products.putIfAbsent(product, 0);
        return product;
    }

    @Override
    public Bill update(Bill bill) {
        int index = bills.indexOf(bill);

        if (index == -1) return null;

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
            filtered = addToListByProduct(filtered, product);

        if (startDate != null)
            filtered = addToListByStartDate(filtered, startDate);

        if (endDate != null)
            filtered = addToListByEndDate(filtered, endDate);

        if (billComparator != null) {

            filtered.sort(billComparator.reversed());

        }
        return filtered;
    }

    private List<Bill> addToListByProduct (List<Bill> listBills,
                                           Product product) {
        List<Bill> list = new ArrayList<>();

        if (product == null) return listBills;

        if (listBills != null)
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
        List<Bill> list = new ArrayList<>();

        if (date == null) return listBills;

        if (listBills != null) {
            for (Bill bill : listBills) {
                if ((date.compareTo(bill.getOpenTime())) >= 0 ) {
                    list.add(bill);
                }
            }
        }
        return list;
    }

    private List<Bill> addToListByEndDate(List<Bill> listBills,
                                          LocalDateTime date) {
        List<Bill> list = new ArrayList<>();

        if (date == null) return listBills;

        if (listBills != null ) {
            for (Bill bill : listBills) {
                if ((date.compareTo(bill.getCloseTime())) <= 0 ) {
                    list.add(bill);
                }
            }
        }
        return list;
    }

    @Override
    public double aggrAmtPrice(Salesman salesman, LocalDateTime startDate,
                               LocalDateTime endDate) {
        double summ = 0.0;
        if (bills == null)
            return summ;
        return aggrAmPrice(filter(salesman,null, startDate, endDate, null));
    }

    private double aggrAmPrice(List<Bill> filteredList){
        int summ = 0;
        if (filteredList == null || filteredList.isEmpty()) return summ;
        for (Bill bill : filteredList) {
//            summ += bill.getAmountPrice();
        }
        return Double.parseDouble(null);


    }

    @Override
    public double averageAmountPrice(Salesman salesman, LocalDateTime startDate,
                                     LocalDateTime endDate) {
        List<Bill> filteredList = filter(salesman,null, startDate, endDate, null);
        if (filteredList == null || filteredList.isEmpty())
            return 0.0;
        return  aggrAmPrice(filteredList) / filteredList.size();
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
