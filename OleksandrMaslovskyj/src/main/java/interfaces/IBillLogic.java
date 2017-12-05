package interfaces;

import exceptions.BillNotFoundException;
import exceptions.UnableToAddProductToBillException;
import exceptions.UnableToCalculatePriceException;
import exceptions.UnableToCloseBillException;
import models.Bill;
import models.Product;

public interface IBillLogic {

    Product addProductToBill(Bill bill, String name)
                throws UnableToAddProductToBillException;
    void closeBill(Bill bill) throws UnableToCloseBillException;

    double calculateAmountPrice(Bill bill)
                                    throws UnableToCalculatePriceException;

    String printBill(Bill bill) throws BillNotFoundException;

}
