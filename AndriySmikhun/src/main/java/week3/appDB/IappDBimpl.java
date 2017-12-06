package week3.appDB;

import week3.model.Bill;
import week3.model.Product;
import week3.model.Salesman;

import java.util.ArrayList;
import java.util.List;

public class IappDBimpl implements IappDB {

    // todo init in constructor
    private List<Salesman> salesmen;
    private List<Bill> bills;
    private List<Product> products;

    public IappDBimpl(List<Salesman> salesmen, List<Bill> bills, List<Product> products) {
        this.salesmen = salesmen;
        this.bills = bills;
        this.products = products;
    }

    public IappDBimpl() {
    }
    //////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean updateBill(Bill newBill, Bill oldBill) {
        for (Bill b: bills
             ) {if (b.equals(oldBill))
                 b = newBill;

        }

        return false;
    }

    @Override
    public boolean removeBill(int id) {
        for (Bill b : bills
                ) {
            if (b.getId() == id) {
                b = null;
                return true;
            }
        }
        return true;
    }

    @Override
    public Bill findeBillByID(int id) {
        for (Bill b : bills
                ) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    @Override
    public boolean saveBill(Bill bill) {
        if (bills.add(bill)) {
            return true;
        }
        return false;
    }

    @Override
    public Salesman findSalemanById(int id) {
        for (Salesman s : salesmen
                ) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean saveSaleman(Salesman salesman) {
        salesmen.add(salesman);
        return true;
    }

    @Override
    public Salesman findSalemanByName(String fullName) {
        for (Salesman s : salesmen) {
            if (s.getFullName().equals(fullName)) {
                return s;
            }
        }
        return null;
    }


    @Override
    public boolean removeSaleman(Salesman salesman) {
        for (Salesman s: salesmen
             ) {s = null;
             return true;

        }
        return false;
    }

    @Override
    public boolean updateSalemen(Salesman newSalesman,Salesman oldSalesman) {
        for (Salesman s:salesmen
             ) {if (s.equals(oldSalesman)){
                 s = newSalesman;
                 return true;
        }

        }
        return false;
    }
    @Override
    public List findBillBySalesman(Salesman salesman){
        List<Bill> filtredBills = new ArrayList<>();
        for (Bill bill: bills) {
            if (bill.getSalesman().equals(salesman)){
                filtredBills.add(bill);
            }
        }
     return filtredBills;
    }




}
