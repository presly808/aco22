package week3.appDB;

import week3.model.Bill;
import week3.model.Product;
import week3.model.Salesman;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IappDBimpl implements IappDB {

    private List<Salesman> salesmen;
    private List<Bill> bills; //= new ArrayList<>();
    private List<Product> products; //= new ArrayList<>();

    public IappDBimpl(List<Salesman> salesmen, List<Bill> bills, List<Product> products) {
        this.salesmen = salesmen;
        this.bills = bills;
        this.products = products;
    }

    public IappDBimpl() {
        this.salesmen = new ArrayList<>();
        this.bills = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    @Override
    public List<Salesman> getSalesmen() {
        return salesmen;
    }

    public List<Bill> getBills() {
        return bills;
    }

    //////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean updateBill(Bill newBill, Bill oldBill) {
        int index = bills.indexOf(oldBill);
        Bill oldValue = bills.set(index, newBill);
        if (oldValue.equals(oldBill)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean removeBill(Bill bill) {
        return bills.remove(bill);
    }

    @Override
    public Bill findeBillByID(int id) {
        return bills.stream().filter(s -> s.getId() == id).findFirst().get();
    }

    @Override
    public boolean saveBill(Bill bill) {
        bill.setOpenTime("13:00 07/12/17");
        return bills.add(bill);
    }

    @Override
    public Salesman findSalemanById(int id) {
        return salesmen.stream().filter(s -> s.getId() == id).findFirst().get();
    }

    @Override
    public boolean saveSaleman(Salesman salesman) {

        salesmen.add(salesman);
        return true;
    }

    @Override
    public Salesman findSalemanByName(String fullName) {
        return salesmen.stream().filter(s -> s.getFullName().equals(fullName)).findFirst().get();
    }

    @Override
    public boolean removeSaleman(Salesman salesman) {
        return salesmen.remove(salesman);
    }

    @Override
    public boolean updateSalemen(Salesman newSalesman, Salesman oldSalesman) {
       int index = salesmen.indexOf(oldSalesman);
        Salesman oldValue = salesmen.set(index,newSalesman);
        if (oldValue.equals(oldSalesman)){return true;}
        return false;
    }

    @Override
    public List findBillBySalesman(Salesman salesman) {
        return bills.stream().filter(s -> s.getSalesman().equals(salesman))
                .collect(Collectors.toList());
    }
}
