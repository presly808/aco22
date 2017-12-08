package ua.artcode.market.interfaces;

import ua.artcode.market.model.Product;
import ua.artcode.market.model.SalesMan;

import java.io.IOException;
import java.util.List;

public interface IAppDB {

    public void remove(Object currentObject) throws IOException;

    public List<? extends Object> getAll(Class currentClass) throws IOException;

    public SalesMan findSalesMan(String login, String pass) throws IOException;

    public Product findProductByCode(int productCode) throws IOException;
}
