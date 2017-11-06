package artcode.shop.personal;

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

    public void addProduct(){

    }

    public void closeAndSaveBill (Bill bill) {

    }

    public Bill findBillById (int id){
        return new Bill(billCount);
    }

    public SalesMan findSalesmanByLoginOrFullname (String loginOrFullName) {
        return new SalesMan("");
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
