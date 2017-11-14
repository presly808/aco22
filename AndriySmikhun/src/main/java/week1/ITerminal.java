package week1;

public interface ITerminal {
    void login(String login, String password);
    boolean createBill(Bill bill, Salesman salesman);
    void addProduct(Bill bill, Product product);
    void closeAndSaveBill(Bill bill);
    void findBillById(int id);
    void findSalesmanByLoginOrFullname(String salesman);
    void getTopNofSalesMan();



}
