public class Terminal {

    Bill [] bills;
    Salesman[] sales;

    public Terminal(Bill[] bills, Salesman[] sales) {
        this.bills = bills;
        this.sales = sales;
    }

    public Terminal() {
    }

    public void login(String login, String password){
        for (int i = 0; i < sales.length; i++){
            if (sales[i].getLogin().equals(login) && sales[i].getLogin().equals(password)){
                sales[i].setStatus(true);
            }else {
                System.out.println("Wrong login or password.");
            }
        }
    }                               //enter login and password
    public void createBill(){}                          //create and open bill
    public void addProduct(){}                          //
    public void closeAndSaveBill(){}                    //close and save bill in toarray
    public void findBillById(){}                        //search bill
    public void findSalesmanByLoginOrFullname(){}       //search SAleman whith data
    public void getTopNofSalesMan(){}                   //
    public void doSomeStatisticStuff(){}                //statistic about products and bill

}
