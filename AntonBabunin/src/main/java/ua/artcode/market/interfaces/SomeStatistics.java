package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface SomeStatistics {

    List<Bill> getSalesmanBills (Salesman salesman);
    Bill findBillById (int id);
    Set<Bill> filter(Salesman salesman, Product product,
                               Date startDate, Date endDate,
                               Comparator<Bill> billComparator);

}
