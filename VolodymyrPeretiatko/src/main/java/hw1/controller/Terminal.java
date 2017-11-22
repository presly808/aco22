package hw1.controller;

import hw1.model.*;
import hw1.utils.*;

import java.util.*;


public class Terminal implements ITerminal {

    private IAppDB appDB = new IAppDBImpl();

    public static Salesman getSalesmanByName(List<Salesman> salesmen, String name){

        if (name == null){
            return null;
        }

        for (Salesman s : salesmen){
            if(name.equals(s.getName())){
                return  s;
            }
        }
        return null;
    }

    public List<Salesman> getSalesmen() {
        return appDB.getSalesmen();
    }

    public ArrayList<Product> getProducts() {
        return (ArrayList<Product>) products.clone();
    }

    public boolean addSalesman(Salesman salesman){
        return appDB.addSalesman(salesman);
    }

    public Salesman login(String name, String pass){
        Salesman salesman = getSalesmanByName(appDB.getSalesmen(), name);
        if (salesman != null && pass.equals(salesman.getPass())){
            return salesman;
        }
        return null;
    }

    public Bill createBill(Salesman salesman){
        Bill b = new Bill(id, salesman);
        return b;
    }

    public boolean closeAndSaveBill(Bill bill){
        return bills.add(bill.closeBill());
    }

    public List<Bill> getBills() {
        return (ArrayList<Bill>)bills.clone();
    }

    public boolean addProduct(Product p) {

        if (!products.contains(p))
            return products.add(p);

        return false;

    }

    public Bill findBillById(int id){
        for (Bill b : bills){
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }

    public Salesman getTopNofSalesMan(){

        Map<Salesman, Double> sales = getSalesAmountBySalesman();

        Salesman topSalesman = null;
        Double amount = 0.0;
        Double currentAmount;

        for (Salesman s : salesmen){

            currentAmount = sales.get(s);
            if (currentAmount == null){
                continue;
            }
            if (currentAmount > amount){
                amount = currentAmount;
                topSalesman = s;
            }
        }
        return topSalesman;
    }

    public Map<Salesman, Double> getSalesAmountBySalesman(){

        HashMap<Salesman, Double> sales = new HashMap<>();
        Salesman salesman;
        for (Bill b : bills){
            salesman = b.getSalesman();
            if (sales.get(salesman) == null){
                sales.put(salesman, b.getAmountPrice());
            } else {
                sales.put(salesman, sales.get(salesman) + b.getAmountPrice());
            }
        }
        return sales;
    }

    public ArrayList<Bill> filter(ArrayList<Salesman> salesmen, ArrayList<Product> products,
                                  Date startDate, Date endDate, Comparator<Bill> comparator){

        ArrayList<Bill> result = new ArrayList<>();
        Date BDate;

        for(Bill b : this.bills){

            BDate = b.getCloseTime();
            if (startDate.compareTo(BDate) > 0 || endDate.compareTo(BDate) < 0){
                continue;
            }

            if (salesmen != null && !salesmen.contains(b.getSalesman())){
                continue;
            }

            if (products != null
                    && !Utils.listContainElementsOther(b.getProducts(), products)){
                continue;
            }

            result.add(b);

        }

        if(comparator != null && !result.isEmpty()) {result.sort(comparator); result.trimToSize();}

        return result;
    }

}
