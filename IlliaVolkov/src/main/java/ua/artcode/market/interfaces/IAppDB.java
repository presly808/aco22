package ua.artcode.market.interfaces;

import ua.artcode.market.model.Product;
import ua.artcode.market.model.SalesMan;

import java.util.List;

public interface IAppDB {

    public void remove(Object currentObject);

    public List<? extends Object> getAll(Class currentClass);

    public SalesMan findSalesMan(String login, String pass);

    public Product findProductByCode(int productCode);
}
