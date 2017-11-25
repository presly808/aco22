package week1.comparators;

import week1.model.Bill;

import java.util.Comparator;

/**
 * Created by ENIAC on 23.11.2017.
 */
public class BillComparatorForSorting implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2) {
        return o1.getTime().getCloseTime().compareTo(o2.getTime().getCloseTime());
    }
}
