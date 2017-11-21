package week1.utils;

import week1.interfaces.IAppDB;
import week1.model.Bill;

public class TerminalUtils {

    public static int calculateSumOfSoldProducts(IAppDB iAppDB) {
        int soldProducts = 0;

        for (Bill bill : iAppDB.getAllBills()) {
            soldProducts += bill.getNextProductId();
        }

        return soldProducts;
    }

    public static double findMaxPriceBill(IAppDB iAppDB) {

        double maxPriceBill = iAppDB.getAllBills().get(0).getAmountPrice();

        for (Bill bill : iAppDB.getAllBills()) {
            if (bill != null && bill.getAmountPrice() > maxPriceBill)
                maxPriceBill = bill.getAmountPrice();
        }

        return maxPriceBill;
    }

    public static double findMinPriceBill(IAppDB iAppDB) {

        double minPriceBill = iAppDB.getAllBills().get(0).getAmountPrice();

        for (Bill bill : iAppDB.getAllBills()) {
            if (bill != null && bill.getAmountPrice() < minPriceBill)
                minPriceBill = bill.getAmountPrice();
        }

        return minPriceBill;
    }

}
