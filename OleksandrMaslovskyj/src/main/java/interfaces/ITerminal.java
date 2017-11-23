package interfaces;

import models.Bill;
import models.Product;
import models.Salesman;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public interface ITerminal {

    Bill createBill(Bill bill);

    Product addProduct(Bill bill, String productName);

    void closeAndSaveBill(Bill bill);

    Bill findBillById(long id) throws NoSuchElementException;

    Salesman findSalesmanByLoginOrFullName(String fullname, String login) throws NoSuchElementException;

    List<Bill> sortBillListByDateCreation();

    List<Bill> getBillsByStartAndEndDates(Date startDate, Date endDate);

    List<Bill> getBillsByCreator(Salesman salesman);

}
