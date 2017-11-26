package week1.models;

public interface ITerminal {

    void signIn(String login, String password);

    boolean createBill(Bill newBill);

    boolean closeAllPreviousBills(Time closeTime);

    boolean addProductToBill(Product newProduct);

    boolean closeAndSaveBill(Time time);

    Bill findBillById(int searchingId);

    Seller findSalesmanByLoginOrFullname(String loginOrNameOfSalesMan);

    Seller[] getTopNofSalesMan(int quantityOfTopSellers);

    String doSomeStatisticStuff();
}
