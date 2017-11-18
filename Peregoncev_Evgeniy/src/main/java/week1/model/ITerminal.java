package week1.model;

/**
 * Created by ENIAC on 19.11.2017.
 */
public interface ITerminal {

    boolean login(String login, String pass);

    Bill createbill(Bill bill);

    void closeAndSaveBill(Bill bill);

    Bill findBillById(Bill[] bill, int billId);

    Salesman findSalesmanByLogin(Salesman[] sales, String searchLogin);

}

