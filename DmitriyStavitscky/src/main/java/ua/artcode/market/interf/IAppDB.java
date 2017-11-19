package ua.artcode.market.interf;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

public interface IAppDB {

    Bill getAll();
    Bill findBillById(int id);
    Salesman findSalesmanById(int id);
    Product findProductById(int id);
    void update(Object obj, int id);


    /*getAll()
    findById(id)
    remove(id)
    update(object, id)*/
}
