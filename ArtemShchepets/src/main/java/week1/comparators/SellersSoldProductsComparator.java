package week1.comparators;

import week1.model.Seller;

import java.util.Comparator;

public class SellersSoldProductsComparator implements Comparator<Seller>{


    @Override
    public int compare(Seller o1, Seller o2) {
        return o1.getSoldProducts() - o2.getSoldProducts();
    }
}
