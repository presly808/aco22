package ua.artcode.market.appdb;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.util.List;

// ctrl + alt + 7 - show all usages
public class AppDB implements IAppDB {

    private List<Bill> bills;

    private Salesman manager;

    private List<Salesman> salesmans;

    private List<Product> products;

    private List<String> historyOfActions;

    private int countOfId;

    public AppDB() {
    }

    public AppDB(List<Bill> bills, Salesman manager, List<Salesman> salesmans, List<Product> products, List<String> historyOfActions, int countOfId) {
        this.bills = bills;
        this.manager = manager;
        this.salesmans = salesmans;
        this.products = products;
        this.historyOfActions = historyOfActions;
        this.countOfId = countOfId;
    }

    @Override
    public int genId() {
        return ++countOfId;
    }

    @Override
    public void addActionToHistory(String message) {
        this.historyOfActions.add(message);
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

    @Override
    public List<Bill> getAllBills() {
        return bills;
    }

    @Override
    public Salesman findSalesmanByLoginOrName(String loginOrName) {
        if (loginOrName == null || loginOrName.isEmpty()) {

            return null;
        }

        return salesmans.stream()
                .filter(u -> u.getFullName().equals(loginOrName) || u.getLogin().equals(loginOrName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Bill findBillById(int id) {
        if (id == 0) {
            return null;
        }

        return bills.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Salesman findSalesmanById(int id) {
        if (id == 0) {
            return null;
        }

        return salesmans.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product findProductById(int id) {
        if (id == 0) return null;

        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Bill removeBill(int id) {
        return bills.remove(bills.indexOf(findBillById(id)));
    }

    @Override
    public Product removeProduct(int id) {
        return products.remove(products.indexOf(findProductById(id)));
    }

    @Override
    public Salesman removeSalesman(int id) {
        return salesmans.remove(salesmans.indexOf(findSalesmanById(id)));
    }


    @Override
    public Bill update(Bill bill) {
        int index = bills.indexOf(findBillById(bill.getId()));

        if (index == -1) {
            System.out.println("bill with id not found" + bill);
            return null;
        }

        return bills.set(index, bill);
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

    public List<String> getHistoryOfActions() {
        return historyOfActions;
    }

    public void setHistoryOfActions(List<String> historyOfActions) {
        this.historyOfActions = historyOfActions;
    }

    public int getCountOfId() {
        return countOfId;
    }

    public void setCountOfId(int countOfId) {
        this.countOfId = countOfId;
    }

    public Salesman getManager() {
        return manager;
    }

    public void setManager(Salesman manager) {
        this.manager = manager;
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
