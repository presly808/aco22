package hw1.controller;

import com.sun.deploy.util.ArrayUtil;
import hw1.model.Bill;
import hw1.model.Product;
import hw1.model.Salesman;
import hw1.utils.ILogger;
import hw1.utils.LogSout;
import hw1.utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;


public class Terminal implements ITerminal {

    private static Terminal uniqueInstance;

    private ArrayList<Bill> bills;
    private ArrayList<Salesman> salesmen;
    private ArrayList<Product> products;

    private ILogger log = new LogSout();

    public static synchronized Terminal getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Terminal();
        }
        return uniqueInstance;
    }

    private Terminal(){
        bills = new ArrayList<>();
        salesmen = new ArrayList<>();
        products = new ArrayList<>();
    }

    public static Salesman getSalesmanByName(ArrayList<Salesman> salesmen, String name){

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

    public boolean addSalesman(Salesman salesman){
        if (log != null)
            log.info(Terminal.class, "add salesman " + salesman);

        if (salesmen.contains(salesman))
            return false;

        return salesmen.add(salesman);
    }

    public Salesman login(String name, String pass){
        Salesman salesman = getSalesmanByName(salesmen, name);
        if (salesman != null && pass.equals(salesman.getPass())){
            if (log != null)
                log.info(Terminal.class, "Salesman logged in " + salesman);
            return salesman;
        }
        if (log != null)
            log.error(Terminal.class, "Wrong authentification data" + name);

        return null;
    }

    public Bill createBill(int id, Salesman salesman){
        Bill b = new Bill(id, salesman);
        if (log != null)
            log.info(Terminal.class, "New bill was created! " + b);

        return b;
    }

    public boolean closeAndSaveBill(Bill bill){
        if (log != null)
            log.info(Terminal.class, "Bill was closed " + bill);

        return bills.add(bill.closeBill());
    }

    public ArrayList<Bill> getBills() {
        return (ArrayList<Bill>)bills.clone();
    }

    public boolean addProduct(Product p){
        if (products.contains(p))
            return false;

        return products.add(p);
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

        HashMap<Salesman, Double> sales = getSalesAmountBySalesman();

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

    public ArrayList<Bill> filter(ArrayList<Salesman> salesmen, ArrayList<Product> products, Date startDate, Date endDate, Comparator<Bill> c){

        ArrayList<Bill> result = new ArrayList<>();
        Date BDate;

        for(Bill b : this.bills){

            BDate = b.getCloseTime();
            if (startDate.compareTo(BDate) < 0 || endDate.compareTo(BDate) > 0){
                continue;
            }

            if (salesmen != null
                    && !salesmen.contains(b.getSalesman())){
                continue;
            }

            if (products != null
                    && !Utils.listContainElementsOther(b.getProducts(), products)){
                continue;
            }

            result.add(b);

        }

        return result;
    }

    public HashMap<Salesman, Double> getSalesAmountBySalesman(){

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

}
