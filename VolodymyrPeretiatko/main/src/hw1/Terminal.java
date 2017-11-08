package hw1;

import java.util.ArrayList;
import java.util.HashMap;


public class Terminal {

    private static Terminal uniqueInstance;

    private ArrayList<Bill> bills;
    private ArrayList<Salesman> salesmen;
    private ArrayList<Product> products;

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

    public boolean addSalesman(Salesman salesman){
        return salesmen.add(salesman);
    }

    public Salesman login(String name, String pass){

        Salesman salesman = Salesman.getSalesmanByName(salesmen, name);

        if (salesman != null && pass.equals(salesman.getPass())){
            return salesman;
        }
        return null;
    }

    public Bill createBill(int id, Salesman salesman){
        return Bill.openBill(id, salesman);
    }

    public boolean closeAndSaveBill(Bill bill){
        return bills.add(bill.closeBill());
    }

    public boolean addProduct(Product p){
        return products.add(p);
    }

    public Bill findBillById(int id){
        return Bill.findBillById(bills, id);
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
