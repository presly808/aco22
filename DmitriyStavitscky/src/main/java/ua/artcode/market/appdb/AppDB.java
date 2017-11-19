package ua.artcode.market.appdb;

import ua.artcode.market.interf.IAppDB;
import ua.artcode.market.models.*;

import java.util.ArrayList;
import java.util.List;

public class AppDB implements IAppDB{

    List<Bill> bills = new ArrayList<>();

    List<Salesman> salesmans = new ArrayList<>();

    List<Product> products = new ArrayList<>();


    @Override
    public Bill getAll() {
        return null;
    }

    @Override
    public Bill findBillById(int id) {
        return null;
    }

    @Override
    public Salesman findSalesmanById(int id) {
        return null;
    }

    @Override
    public Product findProductById(int id) {
        return null;
    }

    @Override
    public void update(Object obj, int id) {

    }
}
