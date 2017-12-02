package ua.artcode.market.view;

import ua.artcode.market.model.Bill;

public class BillComparator implements java.util.Comparator<Bill>{

    @Override
    public int compare(Bill o1, Bill o2) {

        return o1.getCode() - o2.getCode();
    }

    class BillCodeComparator implements java.util.Comparator<Bill> {

        @Override
        public int compare(Bill o1, Bill o2) {

            return o1.getCode() - o2.getCode();
        }
    }

    class BillProductsCountComparator implements java.util.Comparator<Bill> {

        @Override
        public int compare(Bill o1, Bill o2) {
            return o1.getProductsBill().size() - o2.getProductsBill().size();
        }
    }

    class BillAmountPriceComparator implements java.util.Comparator<Bill> {

        @Override
        public int compare(Bill o1, Bill o2) {

            double res = o1.getAmountPrice() - o2.getAmountPrice();

            return res > 0 ? 1 :
                    res < 0 ? -1 : 0;
        }
    }

    class BillSalesmanComparator implements java.util.Comparator<Bill> {

        @Override
        public int compare(Bill o1, Bill o2) {
            return o1.getSalesMan().getFullName().compareTo
                    (o2.getSalesMan().getFullName());
        }
    }

    class BillTimeComparator implements java.util.Comparator<Bill> {

        @Override
        public int compare(Bill o1, Bill o2) {
            return o1.closeTime.compareTo(o2.closeTime);
        }
    }

}

