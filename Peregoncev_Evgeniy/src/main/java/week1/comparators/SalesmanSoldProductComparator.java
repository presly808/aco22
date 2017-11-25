package week1.comparators;

import week1.models.Salesman;

import java.util.Comparator;

/**
 * Created by ENIAC on 23.11.2017.
 */
public class SalesmanSoldProductComparator implements Comparator<Salesman>{

    @Override
    public int compare(Salesman o1, Salesman o2) {

        return o1.getCountSoldProduct() - o2.getCountSoldProduct();
    }
}
