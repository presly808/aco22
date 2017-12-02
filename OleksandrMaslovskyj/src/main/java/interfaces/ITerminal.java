package interfaces;

import exceptions.*;
import models.Bill;
import models.Product;
import models.Salesman;
import java.util.Date;
import java.util.List;

public interface ITerminal {

    Bill createBill(Bill bill) throws IncorrectBillException;

    Product addProduct(Bill bill, String productName)
            throws IncorrectBillException, UnableToAddProductToBillException;

    void closeAndSaveBill(Bill bill) throws IncorrectBillException, UnableToCloseBillException;

    Bill findBillById(long id) throws BillNotFoundException;

    Salesman findSalesmanByLoginOrFullName(String fullname, String login)
                                                throws NoSuchSalesmanException;

    List<Bill> sortBillListByDateCreation();

    List<Bill> getBillsByStartAndEndDates(Date startDate, Date endDate)
                                            throws BillNotFoundException;

    List<Bill> getBillsByCreator(Salesman salesman)
                throws InvalidSalesmanException, BillNotFoundException;

}
