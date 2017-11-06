package artcode.shop.personal;

public class Terminal {

    private static Terminal instance;

    private Bill[] bills;
    private SalesMan[] sales;

    private Terminal(){
    }

    public static Terminal getInstance() {
        if (instance == null) {
            instance = new Terminal();
        }
        return instance;
    }

    public void login (SalesMan salesMan) {

    }

    public Bill createBill(){
        return new Bill();
    }
    public void addProduct(){

    }

    public void closeAndSaveBill (Bill bill) {

    }

    public Bill findBillById (int id){
        return new Bill();
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
}
