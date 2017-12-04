package week1.utils;

import week1.comparators.AmountPriceComparator;
import week1.database.IAppDB;
import week1.model.Bill;

public class TerminalStatisticUtils {

    public static int calculateSumOfSoldProducts(IAppDB iAppDB) {

        return iAppDB.getAllBills().stream().mapToInt(Bill::getNextProductId).sum();
    }

    public static double findMaxPriceBill(IAppDB iAppDB) {

        return iAppDB.getAllBills().stream()
                .max(new AmountPriceComparator()).get().getAmountPrice();
    }

    public static double findMinPriceBill(IAppDB iAppDB) {

        return iAppDB.getAllBills().stream()
                .min(new AmountPriceComparator()).get().getAmountPrice();
    }

}
