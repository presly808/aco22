package ua.artcode.market.interfaces;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Salesman;

import java.util.List;

public interface SomeStatistics {

    List<Bill> getSalesmanBills (Salesman salesman);
}
