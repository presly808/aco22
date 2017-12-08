package week3.appDB;

import week3.model.Bill;
import week3.model.Product;
import week3.model.Salesman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IappDBimpl implements IappDB{

    private List<Salesman> salesmen = new ArrayList<>();
    private List<Bill> bills = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    public IappDBimpl(List<Salesman> salesmen, List<Bill> bills, List<Product> products) {
        this.salesmen = salesmen;
        this.bills = bills;
        this.products = products;
    }

    public IappDBimpl() {
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
        for (Bill b : bills) {
            if (b.equals(oldBill))
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
        return bills.stream().filter(s -> s.getId() == id).findFirst().get();
    }

    @Override
    public boolean saveBill(Bill bill) {
        bill.setOpenTime("13:00 07/12/17");
     return bills.add(bill);
    }
        @Override
        public Salesman findSalemanById ( int id){
            return salesmen.stream().filter(s -> s.getId() == id).findFirst().get();
        }

        @Override
        public boolean saveSaleman (Salesman salesman) {
            if(salesman == null) return false;
            boolean check = salesmen.stream()
                    .anyMatch(s -> s.getId() == salesman.getId());
            if (!check) { return salesmen.add(salesman);}
            return true;
        }

        @Override
        public Salesman findSalemanByName (String fullName){
            return salesmen.stream().filter(s -> s.getFullName().equals(fullName)).findFirst().get();
        }

        @Override
        public boolean removeSaleman (Salesman salesman){
            for (Salesman s : salesmen
                    ) {
                s = null;
                return true;

            }
            return false;
        }

        @Override
        public boolean updateSalemen (Salesman newSalesman, Salesman oldSalesman){
            for (Salesman s : salesmen
                    ) {
                if (s.equals(oldSalesman)) {
                    s = newSalesman;
                    return true;
                }

            }
            return false;
        }
        @Override
        public List findBillBySalesman (Salesman salesman) {
            return bills.stream().filter(s -> s.getSalesman().equals(salesman))
                    .collect(Collectors.toList());
        }
    }
