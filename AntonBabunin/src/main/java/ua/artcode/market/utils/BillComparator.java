package ua.artcode.market.utils;

import ua.artcode.market.models.Bill;

import java.util.Comparator;

public class BillComparator implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2) {
        return Double.compare(o2.getAmountPrice(), o1.getAmountPrice());
    }
}
