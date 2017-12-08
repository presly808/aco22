package week3.controller;

import week3.appDB.IappDB;
import week3.model.Product;
import week3.appDB.IappDBimpl;
import week3.model.Bill;
import week3.model.Salesman;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ITerminalimpl implements ITerminal {


    private IappDB appDB;
    private String user;

    public ITerminalimpl() {
        appDB = new IappDBimpl();
    }

    public ITerminalimpl(IappDB appDB) {
        this.appDB = appDB;
    }

    @Override
    public boolean login(String login, String password) {
        boolean status = appDB.getSalesmen()
                .stream()
                .anyMatch(s -> s.getLogin().equals(login) && s.getPassword().equals(password));
        if (status) {
            user = login;
            return true;
        }
        return false;
    }

    //@Override
    //public boolean logOut(Salesman salesman) {
    //  return false;
    //}

    @Override
    public boolean addProduct(int id, Product product) {
        Bill bill = appDB.findeBillByID(id);
        if (bill != null) {
            bill.getProducts().add(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int idBill, Product product) {
        Bill bill = appDB.findeBillByID(idBill);
        return bill.getProducts().remove(product);
    }


    @Override
    public Bill[] filterBill() {
        return new Bill[0];
    }

    @Override
    public boolean closeBill(Bill bill) {
        bill.everageBill();
        bill.setCloseTime("14:15 07/12/17");

        return false;
    }

    @Override
    public boolean createSalesMan(String login, String password, String fullName) {
        if (login == null || password == null || fullName == null)
            return false;
        int id = (int) Math.random() * 100;
        Salesman salesman = new Salesman(id, login, password, fullName,null);
        return appDB.saveSaleman(salesman);
    }

    @Override
    public void getTopSalesman() {
        Map<Salesman, List<Bill>> groupBill =  appDB.getBills()
                .stream()
                .collect(Collectors.groupingBy(Bill::getSalesman));

        for (Map.Entry <Salesman ,List<Bill>> item: groupBill.entrySet()){
            System.out.println(item.getKey().getFullName());
            item.getValue().stream()
                    .forEach(s -> System.out.println("ID Bill " + s.getId()
                            + " Avarage"  + s.getEverageBill()));


            System.out.println(" ");
        }

    }
}