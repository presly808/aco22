package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.utils.Generator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class IAppDbImpl implements IAppDb {

    private int billNextId;
    private int productNextId;

    private List<Bill> bills;
    private Map<Product,Integer> products;


    public IAppDbImpl() {
        this.bills = new ArrayList<>();
        this.products = Generator.randomProducts(0);
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
    public Map<Product, Integer> getAllProducts() {
        return products;
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
    public Salesman createSalesman(String fullName, String login,
                                   String password) throws IOException {
        return null;
    }

    @Override
    public Salesman login(String login, String password) throws IOException {
        return null;
    }

    @Override
    public Salesman logout(Salesman salesman) {
        return null;
    }

    @Override
    public Salesman findSalesmanByLogin(String login) {
        return null;
    }

    @Override
    public Set<Bill> filter(Salesman salesman, Product product,
                            LocalDateTime startDate, LocalDateTime endDate,
                            Comparator<Bill> billComparator) {
        if (getBills() == null) return null;

        Set<Bill> filtered = new HashSet<>();
        filtered.addAll(getBills());


        if (salesman == null)
            filtered = addToListBySeller(filtered, salesman);

        if (product != null)
            filtered = addToListByProduct(filtered, product);

        if (startDate != null)
            filtered = addToListByStartDate(filtered, startDate);

        if (endDate != null)
            filtered = addToListByEndDate(filtered, endDate);

        if (billComparator != null) {
            Set<Bill> sorted = new TreeSet<>();
            sorted.addAll(filtered);
            return sorted;
        }
        return filtered;



    }

    private Set<Bill> addToListByProduct (Set<Bill> set, Product product) {
        Set<Bill> setBills = new HashSet<>();

        if (product == null) return set;

        if (set != null)
            for (Bill bill : set) {
                if ((bill.getProductsMap().containsKey(product))) {
                    setBills.add(bill);
                }
            }
        return setBills;
    }

    private Set<Bill> addToListBySeller (Set<Bill> set, Salesman salesman) {
        Set<Bill> setBills = new HashSet<>();

        if (salesman == null) return set;

        if (set != null) {
            for (Bill bill : set) {
                if ((salesman.equals(bill.getSalesman()))) {
                    setBills.add(bill);
                }
            }
        }
        return setBills;
    }

    private Set<Bill> addToListByStartDate(Set<Bill> set, LocalDateTime date) {
        Set<Bill> setBills = new HashSet<>();

        if (date == null) return set;

        if (set != null) {
            for (Bill bill : set) {
                if ((date.compareTo(bill.getOpenTime())) >=0 ) {
                    setBills.add(bill);
                }
            }
        }
        return setBills;
    }

    private Set<Bill> addToListByEndDate(Set<Bill> set, LocalDateTime date) {
        Set<Bill> setBills = new HashSet<>();

        if (date == null) return set;

        if (set != null ) {
            for (Bill bill : set) {
                if ((date.compareTo(bill.getCloseTime())) < 0 ) {
                    setBills.add(bill);
                }
            }
        }
        return setBills;
    }


}
