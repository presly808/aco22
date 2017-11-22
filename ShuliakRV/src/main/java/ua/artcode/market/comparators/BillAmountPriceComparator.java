package ua.artcode.market.comparators;

import ua.artcode.market.models.Bill;

import java.util.Comparator;

public class BillAmountPriceComparator implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2) {

        double res = o1.getAmountPrice()-o2.getAmountPrice();

        return res>0 ? 1 : res<0 ? -1 : 0;

    }
}
