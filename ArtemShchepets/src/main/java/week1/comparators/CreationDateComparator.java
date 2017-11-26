package week1.comparators;

import week1.model.Bill;

import java.util.Comparator;

public class CreationDateComparator implements Comparator<Bill> {

    @Override
    public int compare(Bill o1, Bill o2) {
        return o1.getCreationDate().compareTo(o2.getCloseTime());
    }
}
