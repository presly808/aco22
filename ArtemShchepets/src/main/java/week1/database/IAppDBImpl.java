package week1.database;

import week1.interfaces.IAppDB;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;

import java.util.ArrayList;
import java.util.List;

public class IAppDBImpl implements IAppDB {

    private int billNextId;
    private int productNextId;

    private int currentSeller = -1;

    private List<Bill> bills;
    private List<Product> products;
    private List<Seller> sellers;

    public IAppDBImpl() {
        this.bills = new ArrayList<>();
        this.products = new ArrayList<>();
        this.sellers = new ArrayList<>();
    }



    @Override
    public List<Bill> getAllBills() {
        return bills;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public List<Seller> getAllSellers() {
        return sellers;
    }

    @Override
    public int getCurrentSellerId() {
        return this.currentSeller;
    }

    @Override
    public void setCurrentSeller(int currentSellerId) {
        this.currentSeller = currentSellerId;
    }

    @Override
    public Bill findByBillId(int billId) {

        for (Bill bill : bills) {
            if (billId == bill.getId()) return bill;
        }
        return null;
    }

    @Override
    public Product findByProductId(int productId) {
        for (Product product : products) {
            if (productId == product.getId()) return product;
        }
        return null;
    }

    @Override
    public Seller findBySellerLoginOrFullName(String loginOrFullName) {
        for (Seller seller : sellers) {
            if (loginOrFullName.equals(seller.getLogin())
                    || loginOrFullName.equals(seller.getFullName())) return seller;
        }
        return null;
    }

    @Override
    public Seller findBySellerLoginAndPassword(String login, String password) {
        for (Seller seller : sellers) {
            if (login.equals(seller.getLogin())
                    && password.equals(seller.getPassword())) return seller;
        }
        return null;
    }

    @Override
    public Bill saveBill(Bill bill) {

        bill.setId(billNextId++);
        bills.add(bill);

        return bill;
    }

    @Override
    public Product saveProduct(Product product) {

        product.setId(productNextId++);
        products.add(product);

        return product;
    }

    @Override
    public Seller saveSeller(Seller seller) {

        sellers.add(seller);
        return seller;
    }

    @Override
    public Bill removeBill(int billId) {

        Bill found = findByBillId(billId);

        if (found == null) {
            System.out.println("Not found bill with such id");
            return null;
        }

        bills.remove(found);

        return found;
    }

    @Override
    public Product removeProduct(int productId) {

        Product found = findByProductId(productId);

        if (found == null) {
            System.out.println("Not found product with such id");
            return null;
        }

        products.remove(found);

        return found;
    }

    @Override
    public Seller removeSeller(String login) {

        Seller found = findBySellerLoginOrFullName(login);

        if (found == null) {
            System.out.println("Not found seller with such login");
            return null;
        }

        sellers.remove(found);

        return found;
    }

    @Override
    public Seller updateSeller(Seller seller) {
        int index = sellers.indexOf(seller);

        if (index == -1) {
            System.out.println("Not found bill with such id");
            return null;
        }

        return sellers.set(index,seller);
    }

    @Override
    public Bill updateBill(Bill bill) {

        int index = bills.indexOf(bill);

        if (index == -1) {
            System.out.println("Not found bill with such id");
            return null;
        }

        return bills.set(index,bill);
    }
}
