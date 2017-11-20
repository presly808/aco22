package week1.interfaces;

import week1.comparators.CreationDateComparator;
import week1.model.Bill;
import week1.model.Product;

public interface IBill {

    boolean createBill(Bill newBill);

    boolean closeAllPreviousBills();

    boolean addProductToBill(Product newProduct);

    boolean closeAndSaveBill();

    Bill findBillById(int searchingId);

    Bill[] filter(String startDate, String endDate, CreationDateComparator comparator);

}
