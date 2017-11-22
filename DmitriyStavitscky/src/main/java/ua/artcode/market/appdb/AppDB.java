package ua.artcode.market.appdb;

import ua.artcode.market.interf.IAppDB;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.util.ArrayList;
import java.util.List;

public class AppDB implements IAppDB {

    private List<Bill> bills = new ArrayList<>();

    private List<Salesman> salesmans = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    private int countOdId;

    @Override
    public int genId() {

        countOdId++;
        return countOdId;
    }


    @Override
    public void addProductToDataBase(String name, double price) {
        if (name == null || name.isEmpty()) {
            System.out.println("name is empty or null");

        } else if (price <= 0) {
            System.out.println("price must be > 0");

        } else {
            products.add(new Product(name, price, genId()));
        }
    }

    //ctrl + F6 - refactor signature
    // ctrl + D - copy at new line

    @Override
    public Bill getAll() {
        return null;
    }

    @Override
    public Salesman findSalesman(String loginOrName, boolean isLogin) {
        if (loginOrName == null || loginOrName.isEmpty()) return null;

        if (isLogin) {
            for (Salesman salesman : salesmans) {
                if (salesman.getLogin().equals(loginOrName)) return salesman;
            }

        } else {
            for (Salesman salesman : salesmans) {
                if (salesman.getFullName().equals(loginOrName)) return salesman;
            }
        }

        System.out.println("salesman not found");
        return null;
    }

    @Override
    public Bill findBillById(int id) {
        if (id == 0) {
            return null;
        }

        for (Bill bill : bills) {
            if (bill.getId() == id) {
                return bill;
            }
        }

        System.out.println("bill with this id not found");
        return null;
    }

    @Override
    public Salesman findSalesmanById(int id) {

        if (id == 0) {
            return null;
        }

        for (Salesman salesman : salesmans) {
            if (salesman.getId() == id) {
                return salesman;
            }
        }

        System.out.println("salesman with this id not found");
        return null;
    }

    @Override
    public Product findProductById(int id) {
        if (id == 0) {
            return null;
        }

        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }

        System.out.println("product with this id not found");
        return null;
    }

    @Override
    public void update(Object obj, int id) {

    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Salesman> getSalesmans() {
        return salesmans;
    }

    public void setSalesmans(List<Salesman> salesmans) {
        this.salesmans = salesmans;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

/*
public class TestArrayList {

    public static void main(String[] args) {
        List al = new ArrayList();

        al.add("Ivan");
        al.add(45);
        al.add(456, 23);

        al.set(1, 48);

        Object removed = al.remove(1);

        al.add(0, new IPhone());

        Object obj = al.get(2);

        al.size();

        List list = al.subList(1,2);

        Object[] mas = list.toArray();

        for (int i = 0; i < list.size(); i++) {
            Object el = al.get(i);
        }

        // for each

        for(Object curr : al){
            System.out.println(curr);
        }

        int[] nums = {1,2,3,4,5,6};

        int j = 0;
        for (int curr : nums){
            j++;
            curr = 23;
        }

        // must be override equals
        boolean containsRes = al.contains(new IPhone());
        int index = al.indexOf(new IPhone());

        boolean resultIphone = al.remove(new IPhone());

        al.clear();

        boolean emptyRes = al.isEmpty();

        Object o = 78;

        List<String> names = new ArrayList<>();
        names.add("Yura");
        String name = names.get(0);

        Object[] arr1 = names.toArray();
        String[] arr2 = names.toArray(new String[names.size()]);

        List list3 = Arrays.asList(1,2,3,"sdf");
        List list4 = Arrays.asList(arr1);
        List<String> list5 = Arrays.asList("1", "2", "3");

        List<Integer> list6 = new ArrayList<>();

    }
}
*/
