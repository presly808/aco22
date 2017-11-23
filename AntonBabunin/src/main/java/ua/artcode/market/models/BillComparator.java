package ua.artcode.market.models;



import java.util.Comparator;

public class BillComparator  extends Bill implements Comparator<Bill> {


    @Override
    public int compare(Bill o1, Bill o2) {
        return (int)((o1.getAmountPrice() - o2.getAmountPrice())*100);
    }

    @Override
    public Comparator<Bill> thenComparing(Comparator<? super Bill> other) {
        return new Comparator<Bill>() {
            @Override
            public int compare(Bill o1, Bill o2) {
                return o1.getCloseTime().compareTo(o2.getCloseTime());
            }
        };
    }

//    class SalesmanComprator extends BillComparators {
//
//        @Override
//        public int compare(Bill o1, Bill o2) {
//            return compare(o1.getSalesman().getFullName(), o2.getSalesman());
//        }
//    }

}
//return (int)((this.getAmountPrice() - o.getAmountPrice())*100);