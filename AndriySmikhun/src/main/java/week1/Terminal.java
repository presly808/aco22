package week1;

import week1.Product;

import java.util.Comparator;

public class Terminal implements ITerminal, Comparator {

    Bill[] bills = new Bill[20];
    Salesman[] sales = new Salesman[10];

    public Terminal(Bill[] bills, Salesman[] sales) {
        this.bills = bills;
        this.sales = sales;
    }

    public Terminal() {
    }

    @Override
    public void login(String login, String password){
        for (int i = 0; i < sales.length; i++){
            if (sales[i] == null) break;
            if (sales[i].getLogin().equals(login) && sales[i].getLogin().equals(password)){
                sales[i].setStatus(true);
            }else {
                System.out.println("Wrong login or password.");
            }
        }
    }

    @Override
    public boolean createBill(Bill bill, Salesman salesman){
        if(salesman.isStatus()){
            for (int i = 0; i < bills.length; i++){
                if (bills[i] == null){
                    bills[i] = bill;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void addProduct(Bill bill, Product product){
        if (bill.getCloseTime() == null){
            for (int i = 0; i < bill.getProducts().length; i++){
                if (bill.getProducts()[i] == null) {
                    bill.getProducts()[i] = product;
                }
            }
        }

    }

    @Override
    public void closeAndSaveBill(Bill bill){
        if (bill.getCloseTime() == null) {
            bill.setCloseTime("15:20");
        }
    }                    //close and save bill in array

    @Override
    public void findBillById(int id){
        for (int i = 0; i < bills.length; i++ ){
            if (bills[i] == null) break;
            if(bills[i].getId() == id){
                bills[i].printBill();
            }
        }
    }                        //search bill
    @Override
    public void findSalesmanByLoginOrFullname(String salesman){
        for (int i = 0; i < salesman.length(); i++){
            if (sales[i] == null) break;
            if (sales[i].getLogin().equals(salesman) || sales[i].getFullname().equals(salesman)){
                sales[i].printSalesman();
            }
        }
    }

    @Override
    public void getTopNofSalesMan(){
        int id = 0;
        int[] count = new int[sales.length];
        for (int i = 0; i < sales.length; i++){
            if (sales[i] == null) break;
            count[i] = 0;
            for (int y = 0; y < bills.length; i++){
                if (bills[y].getSalesman().equals(sales[i])){
                    count[i]++;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < count.length; i++){
            if (max < count[i]){
                id = i;
                max = count[i];
            }
        }

        System.out.println("Top Salesman " + sales[id].getFullname() + " Summ Bill = " + max);
    }                   //
    public void doSomeStatisticStuff(){
        System.out.println("Max Bill " + maxBill());
        System.out.println("Min Bill " + minBill());
        System.out.println("Average bill " + averageBill());

    }

    public int maxBill(){
        int id = 0;
        double max = 0;
        for (int i = 0; i < bills.length; i++){
            if (bills[i] == null) break;
            if (max < bills[i].amountPrice){
                id = i;
                max = bills[i].getAmountPrice();
            }
        }
        return id;
    }

    public int minBill(){
        int id = 0;
        double min = 0;
        for (int i = 0; i < bills.length; i++){
            if (bills[i] == null) break;
            if (min > bills[i].amountPrice){
                id = i;
                min = bills[i].getAmountPrice();
            }
        }
        return id;

    }

    public double averageBill(){
        double average = 0.0d;
        int count = 0;
        for (int i = 0; i < bills.length; i++){
            if (bills[i] == null) break;
            average += bills[i].getAmountPrice();
            count++;
        }
        average = average / count;
        return average;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Product product1 = (Product)o1;
        Product product2 = (Product)o2;
        int result = product1.getName().compareTo(product2.getName());

return 0;
    }
}
