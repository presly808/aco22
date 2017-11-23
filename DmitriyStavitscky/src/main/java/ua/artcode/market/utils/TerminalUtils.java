package ua.artcode.market.utils;

import ua.artcode.market.models.Bill;

import java.util.Arrays;

public class TerminalUtils {

    public static Bill billWithMaxAmount(Bill[] bills) {
        if (bills == null) {
            return null;
        }

        double maxAmount = bills[0].getAmountPrice();
        int billIdWithMaxAmount = 0;

        for (int i = 1; i < bills.length; i++) {
            if (bills[i] != null && bills[i].getAmountPrice() > maxAmount) {
                maxAmount = bills[i].getAmountPrice();
                billIdWithMaxAmount = i;
            }
        }

        return bills[billIdWithMaxAmount];
    }

    public static Bill billWithMinAmount(Bill[] bills) {
        if (bills == null) {
            return null;
        }

        double minAmount = bills[0].getAmountPrice();
        int billIdWithMinAmount = 0;

        for (int i = 1; i < bills.length; i++) {
            if (bills[i] != null && bills[i].getAmountPrice() < minAmount) {
                minAmount = bills[i].getAmountPrice();
                billIdWithMinAmount = i;
            }
        }

        return bills[billIdWithMinAmount];
    }

    public static Bill[] splitBillArr(Bill[] bills, int countFiltBills) {
        Bill[] billsRes = Arrays.copyOf(bills, countFiltBills);
        for (Bill billsRe : billsRes) {
            billsRe.setProducts(Arrays.copyOf(billsRe.getProducts(),
                    billsRe.getProductsCount()));
        }

        return billsRes;
    }
}
