package interfaces;

import exceptions.BillNotFoundException;
import exceptions.UnableToAddProductToBillException;
import exceptions.UnableToCalculatePriceException;
import exceptions.UnanleToCloseBillException;
import models.Bill;
import models.Product;

public interface IBillLogic {

    Product addProductToBill(Bill bill, String name)
                throws UnableToAddProductToBillException;
    void closeBill(Bill bill) throws UnanleToCloseBillException;

    double calculateAmountPrice(Bill bill)
                                    throws UnableToCalculatePriceException;

    String printBill(Bill bill) throws BillNotFoundException;

}
