package ua.artcode.market.comparators;

import ua.artcode.market.models.Bill;

import java.util.Comparator;

public class BillIdComparator implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2)
    {
        return o1.getId() - o2.getId();
    }
}


