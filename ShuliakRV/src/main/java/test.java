package ua.artcode.market;

import ua.artcode.market.comparators.BillIdComparator;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {

        List<Bill> arr = new ArrayList<>();

        arr.add(new Bill(new Salesman("ABC","abc","123")));
        arr.get(0).setId(2);
        arr.get(0).setClosed(true);
        arr.get(0).setCloseTime(LocalDateTime.now());
        arr.add(new Bill(new Salesman("DFG","dfg","456")));
        arr.get(1).setId(1);
        arr.get(1).setClosed(true);
        arr.get(1).setCloseTime(LocalDateTime.now());
        arr.add(new Bill(new Salesman("CVB","cvb","789")));
        arr.get(2).setId(3);
        arr.get(2).setClosed(true);
        arr.get(2).setCloseTime(LocalDateTime.now());

        arr.sort(new BillIdComparator());

        System.out.println(arr);
    }

}
