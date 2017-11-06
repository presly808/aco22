package artcode.shop.personal;

import artcode.shop.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Terminal {

    private static Terminal instance;

    private List<Bill> bills;
    private List<SalesMan> sales;

    private int billCount = 0;

    private Terminal(){
    }

    public static Terminal getInstance() {
        if (instance == null) {
            instance = new Terminal();
        }
        return instance;
    }

    public void login (SalesMan salesMan) {
        if (salesMan != null && salesMan.getFullName() != null && salesMan.getFullName().length() > 0){

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter your login");
            String enterLogin = scanner.nextLine();
            //           String enterLogin = "Anton";
            System.out.println(enterLogin);
            System.out.println("Enter your password");
//             String enterPassword = "Anton";

            String enterPassword = scanner.nextLine();
            System.out.println(enterPassword);

            if (enterLogin.equals((salesMan.getLogin()))
                    && enterPassword.equals(salesMan.getPassword())) {

                if (sales == null) {
                sales = new ArrayList();
                }
                sales.add(salesMan);
            }
        }
    }

    public Bill createBill(){
        return new Bill(++billCount);
    }

    public void addProduct(SalesMan salesMan, Product product, Bill bill){
        if (salesMan != null && product != null && bill != null) {
            salesMan.addProduct(product, bill);
        }
    }

    public void closeAndSaveBill (Bill bill) {
        if (bill != null) {
            bill.closeBill();
            if (bills == null) {
                bills = new ArrayList<Bill>();
            }
            bills.add(bill);
        }
    }

    public Bill findBillById (int id){
        Bill returnBill = null;
        if (bills != null) {
            for (Bill bill : bills) {
                if (bill.getId() == id) {
                    returnBill = bill;
                    break;
                }
            }
        }
        return returnBill;
    }

    public SalesMan findSalesmanByLoginOrFullname (String loginOrFullName) {
        SalesMan salesMan = null;
        if (loginOrFullName != null || !loginOrFullName.equals("")) {
            if (sales != null) {
                for (SalesMan salesMan1 : sales) {
                    if (loginOrFullName.equals(salesMan1.getFullName()) || loginOrFullName.equals(salesMan1.getLogin())) {
                        salesMan = salesMan1;
                    }
                }
            }
        }
        return salesMan;
    }

    public SalesMan getTopNofSalesMan () {
        return new SalesMan("");
    }

    public void doSomeStatisticStuff () {

    }

    private double getMax (){
        return 0.0;
    }

    private double getMin (){
        return 0.0;
    }

    private double getAverage (){
        return 0.0;
    }
    private int countSoldProducts () {
        return 0;
    }

    public List<SalesMan> getSales() {
        return sales;
    }

}
