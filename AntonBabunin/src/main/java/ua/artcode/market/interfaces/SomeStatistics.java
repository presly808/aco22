package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public interface SomeStatistics {

    List<Bill> getSalesmanBills (Salesman salesman);
    Bill findBillById (int id);
//    List<Bill> filterMethod (Salesman salesman);
    List<Bill> filterMethodAll (Salesman salesman, Product product,
                                Date startDate, Date endDate,
                                Comparator<Bill> billComparator);

}
