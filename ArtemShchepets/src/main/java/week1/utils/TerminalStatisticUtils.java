package week1.utils;

import week1.database.IAppDB;
import week1.model.Bill;

public class TerminalStatisticUtils {

    public static int calculateSumOfSoldProducts(IAppDB iAppDB) {
        int soldProducts = 0;

        for (Bill bill : iAppDB.getAllBills()) {
            soldProducts += bill.getNextProductId();
        }

        //logger.info("SumOfSoldProducts was calculated");
        return soldProducts;
    }

    public static double findMaxPriceBill(IAppDB iAppDB) {

        double maxPriceBill = iAppDB.getAllBills().get(0).getAmountPrice();

        for (Bill bill : iAppDB.getAllBills()) {
            if (bill != null && bill.getAmountPrice() > maxPriceBill)
                maxPriceBill = bill.getAmountPrice();
        }

        //logger.info("maxPriceBill was found");
        return maxPriceBill;
    }

    public static double findMinPriceBill(IAppDB iAppDB) {

        double minPriceBill = iAppDB.getAllBills().get(0).getAmountPrice();

        for (Bill bill : iAppDB.getAllBills()) {
            if (bill != null && bill.getAmountPrice() < minPriceBill)
                minPriceBill = bill.getAmountPrice();
        }

        //logger.info("minPriceBill was found.");
        return minPriceBill;
    }

}
