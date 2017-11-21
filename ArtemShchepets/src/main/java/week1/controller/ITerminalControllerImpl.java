package week1.controller;

import week1.comparators.SellersSoldProductsComparator;
import week1.interfaces.IAppDB;
import week1.interfaces.ITerminalController;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;
import week1.model.Statistic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static week1.utils.TerminalUtils.calculateSumOfSoldProducts;
import static week1.utils.TerminalUtils.findMaxPriceBill;
import static week1.utils.TerminalUtils.findMinPriceBill;

public class ITerminalControllerImpl implements ITerminalController {

    private final static Logger logger = Logger.getLogger(ITerminalControllerImpl.class.getName());

    private IAppDB iAppDB;

    public ITerminalControllerImpl(IAppDB iAppDB) {
        this.iAppDB = iAppDB;
    }

    @Override
    public boolean login(String login, String password) {

        iAppDB.setCurrentSeller(-1);

        if ((login == null || password == null) ||
                (login.equals("") || password.equals(""))
                || iAppDB.getAllSellers().size() == 0) {

            logger.warning("Login or pass is invalid or sellers is null!");
            return false;
        }

        int sellerId = iAppDB.getAllSellers().
                indexOf(iAppDB.findBySellerLoginAndPassword(login, password));

        if (sellerId == -1) {
            logger.warning("There aren't any sellers with such login\\pass!");
            return false;
        }

        iAppDB.setCurrentSeller(sellerId);

        logger.info("Successfully logged in!");
        return true;
    }

    @Override
    public Bill createBill() {

        Bill bill = new Bill();

        if (iAppDB.getCurrentSellerId() != -1)
            bill.setSeller(iAppDB.getAllSellers().get(iAppDB.getCurrentSellerId()));

        Bill billWithId = iAppDB.saveBill(bill);

        logger.info("Method createBill done.");
        return billWithId;
    }

    @Override
    public Bill addProduct(int billId, Product product) {

        Bill bill = iAppDB.findByBillId(billId);

        if (bill == null) {
            logger.warning("Not found a bill with such id");
            return null;
        }

        product.setId(bill.getNextProductId());
        boolean add = bill.getProductList().add(product);
        bill.setNextProductId(bill.getNextProductId() + 1);
        bill.setAmountPrice(bill.getAmountPrice() + product.getPrice());

        iAppDB.updateBill(bill);

        logger.info("Method addProduct done");
        return bill;
    }

    @Override
    public List<Bill> getAllBills() {
        return iAppDB.getAllBills();
    }

    @Override
    public Bill closeBill(int billId) {

        //TODO ADD SAAAAAAVE OF BILL
        Bill bill = iAppDB.findByBillId(billId);

        if (bill == null) {
            logger.warning("Not found a bill with such id");
            return null;
        }

        bill.setClosed(true);
        bill.setCloseTime(LocalDateTime.now());

        iAppDB.updateBill(bill);

        logger.info("Bill was closed!");
        return bill;
    }

    @Override
    public Bill findBillById(int billId) {

        logger.info("Method findBillById was done.");
        return iAppDB.findByBillId(billId);
    }

    @Override
    public Seller findSellerByLoginOrFullName(String loginOrFullName) {

        logger.info("Method findSellerBy... was done.");
        return iAppDB.findBySellerLoginOrFullName(loginOrFullName);
    }

    @Override
    public Seller getTopOfSalesman() {

        if (iAppDB.getAllSellers().size() == 0) {
            logger.warning("Sellers is null");
            return null;
        }

        for (Seller seller : iAppDB.getAllSellers()) {
            seller = calculateSellerSoldProducts(seller);

            iAppDB.updateSeller(seller);
        }

        // sort sellers by sold products
        iAppDB.getAllSellers().sort(new SellersSoldProductsComparator());

        logger.info("Method getTopOfSalesman was done.");
        return iAppDB.getAllSellers().get(iAppDB.getAllSellers().size() - 1);
    }

    private Seller calculateSellerSoldProducts(Seller seller) {
        for (Bill bill : iAppDB.getAllBills()) {
            if (seller.getFullName() != null && seller.getSoldProducts() == 00
                    && seller.getFullName().equals(bill.getSeller().getFullName())) {
                seller.setSoldProducts(seller.getSoldProducts() + bill.getProductList().size());
            }
        }

        logger.info("Seller sold products were calculated.");
        return seller;
    }

    @Override
    public Statistic doSomeStatisticStuff() {

        Statistic statistic = new Statistic();

        if (iAppDB.getAllSellers() == null || iAppDB.getAllBills() == null) {
            logger.warning("Sry, we haven't any bills or sellers!");
            return null;
        }

        statistic.setMaxBillPrice(findMaxPriceBill(iAppDB));
        statistic.setMinBillPrice(findMinPriceBill(iAppDB));
        statistic.setSoldProducts(calculateSumOfSoldProducts(iAppDB));
        statistic.setBestSalesMan(getTopOfSalesman());

        logger.info("Method doStatisticStuff was done.");
        return statistic;
    }

    @Override
    public List<Bill> filter(LocalDateTime startTime, LocalDateTime endTime, Comparator<Bill> comparator) {

        List<Bill> billList = new ArrayList<>();

        for (Bill bill : iAppDB.getAllBills()) {
            if (bill.getOpenTime().compareTo(startTime) > 0 &&
                    bill.getOpenTime().compareTo(endTime) < 0)
                billList.add(bill);
        }

        billList.sort(comparator);

        logger.info("Method filter was done.");
        return billList;
    }

    @Override
    public void turnOffLogger() {
        logger.setLevel(Level.OFF);
    }
}
