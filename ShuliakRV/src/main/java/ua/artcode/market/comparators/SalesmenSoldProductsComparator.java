package ua.artcode.market.comparators;

import ua.artcode.market.models.Salesman;

import java.util.Comparator;

public class SalesmenSoldProductsComparator implements Comparator<Salesman> {

    @Override
    public int compare(Salesman o1, Salesman o2) {

        return o2.getSoldProducts()-o1.getSoldProducts();
    }
}
