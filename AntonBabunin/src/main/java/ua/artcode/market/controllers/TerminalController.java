package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ITerminal;
import ua.artcode.market.interfaces.SomeStatistics;
import ua.artcode.market.models.*;

import java.util.*;


public class TerminalController implements ITerminal, SomeStatistics{

    private static Map<Salesman, String> salesmanLogin;
    private static Map<String, String> loginPassword;

    private BillController billController;

    public TerminalController() {
        this.billController = new BillController();
    }



    @Override
    public Bill createBill(Terminal terminal, Salesman salesman) {
        if (terminal == null || salesman == null) return null;

        if (!terminal.getSales().contains(salesman)) {
            login(terminal,salesman);
        }
        Bill bill = new Bill();
        bill.setSalesman(salesman);
        terminal.getSales().add(salesman);
        terminal.getBills().add(bill);
        return this.billController.getBills().add(bill) ? bill : null;

    }

    private void login(Terminal terminal, Salesman salesman) {
        login(terminal, salesman.getFullName(), salesman.getPassword());
    }

    @Override
    public boolean addProduct(Bill bill, Product product) {
        return bill != null && product != null &&
                this.billController.getBills().contains(bill) &&
                this.billController.addProduct(bill, product);

    }

    @Override
    public boolean closeAndSafeBill(Bill bill) {
        if (bill != null && billController.closeBill(bill)) {
            bill.setAmountPrice(billController.getAmountPrice());
            billController.printBill(bill);
            return true;
        }
        return false;
    }

    @Override
    public Salesman login(Terminal terminal, String login, String pass) {
        if (terminal == null || login == null || pass == null) return null;
        if (salesmanLogin == null || salesmanLogin.isEmpty()) return null;

        for (Map.Entry<String, String> pair : loginPassword.entrySet()) {
            if (login.equals(pair.getKey()) && pass.equals(pair.getValue())){
                for (Map.Entry<Salesman, String> pair1 :
                        salesmanLogin.entrySet()) {
                    if (login.equals(pair1.getValue())) {
                        Salesman salesman = pair1.getKey();
                        terminal.getSales().add(salesman);
                        return salesman;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Bill> getSalesmanBills (Salesman salesman) {
        ArrayList<Bill> bills = null;
        if (salesman != null) {
            for (Bill bill : billController.getBills()) {
                if (salesman.equals(bill.getSalesman())) {
                    if (bills == null) {
                        bills = new ArrayList<Bill>();
                    }
                    bills.add(bill);
                }
            }
        }
        return bills;
    }

    @Override
    public Bill findBillById(int id) {
        for (Bill bill : billController.getBills()) {
            if (bill.getBillId() == id) {
                return bill;
            }
        }
        return null;
    }

    @Override
    public Salesman create (String fullName, String login, String password) {
        if (fullName == null || login == null || password == null)
            return null;
        Salesman seller =
                new Salesman(fullName, login, password);

        if (salesmanLogin == null) {
            salesmanLogin = new HashMap<Salesman, String>();
            loginPassword = new HashMap<String, String>();
        }

        if (salesmanLogin.isEmpty() || !salesmanLogin.containsValue(login)) {
            salesmanLogin.put(seller, login);
            loginPassword.put(login, password);
            return seller;
        }
        if (salesmanLogin.containsValue(login)) {
            return seller;
        }
        return null;
    }

    @Override
    public Set<Bill> filterMethodAll(Salesman salesman, Product product,
                                     Date startDate, Date endDate,
                                     Comparator<Bill> billComparator) {

        Set<Bill> filtered = new HashSet<>();
        filtered.addAll(billController.getBills());

        if  (salesman == null) return filtered;
        else {
            filtered = addToListBySeller(filtered, salesman);
        }
        if  (product == null) return filtered;
        else {
            filtered = addToListByProduct(filtered, product);
        }
        if  (startDate == null) return filtered;
        else {
            filtered = addToListByStartDate(filtered, startDate);
        }
        if  (endDate == null) return filtered;
        else {
            filtered = addToListByEndDate(filtered, endDate);
        }

        return filtered;

    /*    List<Object> objects = new ArrayList<Object>();
        objects.add(salesman);
        objects.add(product);
        objects.add(startDate);
        objects.add(endDate);
        objects.add(billComparator);

        List<Bill> filtered = billController.getBills();

        for (Object obj : objects)
            if (obj != null) {
                filtered = filter(filtered, obj, objects.indexOf(obj));
            }
        return filtered;*/
    }

/*    private Set<Bill> filter(Set<Bill> set, Object object, int i) {
        if (object == null) return set;
        List<Bill> arrList = new ArrayList<Bill>();
        if (object instanceof Salesman && i == 0) {
            Salesman obj = (Salesman) object;
            arrList = addToListBySeller(set, obj);

        }
        if (object instanceof Product && i == 1) {
            Product obj = (Product) object;
            arrList = addToListByProduct(set, obj);
        }
        if (object instanceof Date) {
            Date obj = (Date) object;
            if (i == 2) {
                arrList = addToListByStartDate(set, obj);
            }
            if (i == 3) {
                arrList = addToListByEndDate(set, obj);
            }
        }
//        if (object instanceof Comparator) {
//            Comparator obj = (Comparator) object;
//            Set<Bill> sorted = sortByComparator(set, obj);
//            for (Bill sort : sorted) {
//                arrList.add(sort);
//            }
//        }
        return arrList;
    }*/

    private Set<Bill> addToListByProduct (Set<Bill> set, Product product) {
        Set<Bill> setBills = new HashSet<>();
        if (set != null)
            for (Bill bill : set) {
                if ((bill.getProducts().containsKey(product))) {
                    setBills.add(bill);
                }
            }
        return setBills;
    }

    private Set<Bill> addToListBySeller (Set<Bill> set, Salesman salesman) {
        Set<Bill> setBills = new HashSet<>();
        if (set != null) {
            for (Bill bill : set) {
                if ((salesman.equals(bill.getSalesman()))) {
                    setBills.add(bill);
                }
            }
        }
        return setBills;
    }

    private Set<Bill> addToListByStartDate(Set<Bill> set, Date date) {
        Set<Bill> setBills = new HashSet<>();
        if (set != null) {
            for (Bill bill : set) {
                if ((date.compareTo(bill.getOpenTime())) >=0 ) {
                    setBills.add(bill);
                }
            }
        }
        return setBills;
    }

    private Set<Bill> addToListByEndDate(Set<Bill> set, Date date) {
        Set<Bill> setBills = new HashSet<>();
        if (set != null) {
            for (Bill bill : set) {
                if ((date.compareTo(bill.getCloseTime())) < 0 ) {
                    setBills.add(bill);
                }
            }
        }
        return setBills;
    }

//    private Set sortByComparator(List<Bill> list, Comparator<Bill> comp) {
//        Set treeSet = new TreeSet();
//        treeSet.addAll(list);
//        return treeSet;
//    }
}
