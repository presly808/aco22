package main.java.ua.artcode.market.Utils;

import main.java.ua.artcode.market.models.Bill;

import java.util.List;

public class TerminalUtils {


    public static Bill billWithMaxAmount(List<Bill> bills) {
        if (bills == null) {
            return null;
        }

        double maxAmount = bills.get(0).getAmountPrice();
        int billIdWithMaxAmount = 0;

        for (int i = 1; i < bills.size(); i++) {
            if (bills.get(i) != null && bills.get(i).getAmountPrice() > maxAmount) {
                maxAmount = bills.get(i).getAmountPrice();
                billIdWithMaxAmount = i;
            }
        }

        return bills.get(billIdWithMaxAmount);
    }

    public static Bill billWithMinAmount(List<Bill> bills) {
        if (bills == null) {
            return null;
        }

        double minAmount = bills.get(0).getAmountPrice();
        int billIdWithMinAmount = 0;

        for (int i = 1; i < bills.size(); i++) {
            if (bills.get(i) != null && bills.get(i).getAmountPrice() < minAmount) {
                minAmount = bills.get(i).getAmountPrice();
                billIdWithMinAmount = i;
            }
        }

        return bills.get(billIdWithMinAmount);
    }
}
