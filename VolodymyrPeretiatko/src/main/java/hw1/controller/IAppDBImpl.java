package hw1.controller;

import hw1.model.*;

import java.util.ArrayList;
import java.util.List;

public class IAppDBImpl implements IAppDB {

    private List<Bill> bills;
    private List<Salesman> salesmen;
    private List<Product> products;

    private int indexBill;
    private int indexSalesman;
    private int indexProduct;

    public IAppDBImpl() {
        bills = new ArrayList<>();
        salesmen = new ArrayList<>();
        products = new ArrayList<>();
    }

    @Override
    public DBItem create(Class c){
        if(c == Bill.class){
            return new Bill(++indexBill);
        }
        if (c == Product.class){
            return new Product(++indexSalesman);
        }
        if (c == Salesman.class){
            return new Salesman(++indexProduct);
        }
        return null;
    }

    @Override
    public DBItem save(DBItem item) {
        if (item instanceof Bill) {
            bills.add((Bill) item);
        } else if (item instanceof Product){
            products.add((Product) item);
        } else if (item instanceof Salesman){
            salesmen.add((Salesman) item);
        } else {
            return null;
        }
        return item;
    }

    @Override
    public DBItem update(DBItem item) {
        int index;
        if (item instanceof Bill) {
            index = bills.indexOf(findById(item.getId(), Bill.class));
            if(index == -1){
                return null;
            }
            return bills.set(index, (Bill) item);
        } else if (item instanceof Product){
            index = products.indexOf(findById(item.getId(), Product.class));
            if(index == -1){
                return null;
            }
            return products.set(index, (Product) item);
        } else if (item instanceof Salesman){
            index = bills.indexOf(findById(item.getId(), Salesman.class));
            if(index == -1){
                return null;
            }
            return salesmen.set(index, (Salesman) item);
        }

        return null;
    }

    @Override
    public DBItem findById(int id, Class c) {

        if(c == Bill.class){
            for (Bill item : bills){
                if(item.getId() == id){
                    return item;
                }
            }
        }
        if (c == Product.class){
            for (Product item : products){
                if(item.getId() == id){
                    return item;
                }
            }
        }
        if (c == Salesman.class){
            for (Salesman item : salesmen){
                if(item.getId() == id){
                    return item;
                }
            }
        }
        return null;

    }

    @Override
    public List<DBItem> getAll(Class c) {
        if(c == Bill.class){
            return new ArrayList<>(bills);
        }
        if (c == Product.class){
            return new ArrayList<>(products);
        }
        if (c == Salesman.class){
            return new ArrayList<>(salesmen);
        }
        return null;
    }

}
