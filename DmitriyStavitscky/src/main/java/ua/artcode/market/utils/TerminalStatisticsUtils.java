package ua.artcode.market.utils;

import ua.artcode.market.models.Bill;

import java.util.List;

public class TerminalStatisticsUtils {

    public static Bill billWithMaxAmount(List<Bill> bills) {
        if (bills == null) {
            return null;
        }

        return bills.stream().max(new Bill.AmountPriceComparator()).orElse(null);
    }

    public static Bill billWithMinAmount(List<Bill> bills) {
        if (bills == null) {
            return null;
        }

        return bills.stream().min(new Bill.AmountPriceComparator()).orElse(null);
    }
}
