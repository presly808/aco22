public class Terminal {

    Bill [] bills;
    Salesman[] sales;

    public Terminal(Bill[] bills, Salesman[] sales) {
        this.bills = bills;
        this.sales = sales;
    }

    public Terminal() {
    }

    public void login(){};                               //enter login and password
    public void createBill(){};                          //create and open bill
    public void addProduct(){};                          //
    public void closeAndSaveBill(){};                    //close and save bill in toarray
    public void findBillById(){};                        //search bill
    public void findSalesmanByLoginOrFullname(){};       //search SAleman whith data
    public void getTopNofSalesMan(){};                   //
    public void doSomeStatisticStuff(){};                //statistic about products and bill

}
