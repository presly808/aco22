package week1.comparators;

import week1.model.Bill;

import java.util.Comparator;

public class AmountPriceComparator implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2) {
        return (int) (o1.getAmountPrice() - o2.getAmountPrice());
    }
}
