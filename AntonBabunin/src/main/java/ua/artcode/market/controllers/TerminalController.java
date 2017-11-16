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
    public List<Bill> filterMethodAll(Salesman salesman, Product product,
                                      Date startDate, Date endDate,
                                      Comparator<Bill> billComparator) {

        List<Object> objects = new ArrayList<Object>();
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
        return filtered;
    }

    private List<Bill> filter(List<Bill> list, Object object, int i) {
        if (object == null) return list;
        List<Bill> arrList = new ArrayList<Bill>();
        if (object instanceof Salesman && i == 0) {
            Salesman obj = (Salesman) object;
            arrList = addToListBySeller(list, obj);

        }
        if (object instanceof Product && i == 1) {
            Product obj = (Product) object;
            arrList = addToListByProduct(list, obj);
        }
        if (object instanceof Date) {
            Date obj = (Date) object;
            if (i == 2) {
                arrList = addToListByStartDate(list, obj);
            }
            if (i == 3) {
                arrList = addToListByEndDate(list, obj);
            }
        }
//        if (object instanceof Comparator) {
//            Comparator obj = (Comparator) object;
//            Set<Bill> sorted = sortByComparator(list, obj);
//            for (Bill sort : sorted) {
//                arrList.add(sort);
//            }
//        }
        return arrList;
    }

    private List<Bill> addToListByProduct (List<Bill> list, Product product) {
        List<Bill> arrList = new ArrayList<Bill>();
        if (list != null)
            for (Bill bill : list) {
                if ((bill.getProducts().containsKey(product))) {
                    arrList.add(bill);
                }
            }
        return arrList;
    }

    private List<Bill> addToListBySeller (List<Bill> list, Salesman salesman) {
        List<Bill> arrList = new ArrayList<Bill>();
        if (list != null) {
            for (Bill bill : list) {
                if ((salesman.equals(bill.getSalesman()))) {
                    arrList.add(bill);
                }
            }
        }
        return arrList;
    }

    private List<Bill> addToListByStartDate(List<Bill> list, Date date) {
        List<Bill> arrList = new ArrayList<Bill>();
        if (list != null) {
            for (Bill bill : list) {
                if ((date.compareTo(bill.getOpenTime())) >=0 ) {
                    arrList.add(bill);
                }
            }
        }
        return arrList;
    }

    private List<Bill> addToListByEndDate(List<Bill> list, Date date) {
        List<Bill> arrList = new ArrayList<Bill>();
        if (list != null) {
            for (Bill bill : list) {
                if ((date.compareTo(bill.getCloseTime())) < 0 ) {
                    arrList.add(bill);
                }
            }
        }
        return arrList;
    }

//    private Set sortByComparator(List<Bill> list, Comparator<Bill> comp) {
//        Set treeSet = new TreeSet();
//        treeSet.addAll(list);
//        return treeSet;
//    }
}
