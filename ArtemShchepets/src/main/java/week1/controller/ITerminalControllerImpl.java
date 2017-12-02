package week1.controller;

import week1.comparators.SellersSoldProductsComparator;
import week1.database.IAppDB;
import week1.exception.AppException;
import week1.exception.MyLoginException;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;
import week1.model.SalesStatistic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static week1.utils.TerminalStatisticUtils.calculateSumOfSoldProducts;
import static week1.utils.TerminalStatisticUtils.findMaxPriceBill;
import static week1.utils.TerminalStatisticUtils.findMinPriceBill;

public class ITerminalControllerImpl implements ITerminalController {

    private final static Logger logger = Logger.getLogger(ITerminalControllerImpl.class.getName());

    private IAppDB iAppDB;

    private int currentSeller = -1;

    public ITerminalControllerImpl(IAppDB iAppDB) {
        this.iAppDB = iAppDB;
        logger.setLevel(Level.OFF);
    }

    @Override
    public boolean login(String login, String password) {

        // set current seller in db to "-1", logging out
        currentSeller = -1;

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

        currentSeller = sellerId;

        logger.info("Successfully logged in!");
        return true;
    }

    @Override
    public Bill createBill() throws AppException {

        Bill bill = new Bill();

        if (currentSeller == -1) {
            throw new MyLoginException("You must login before invoking methods");
        } else {
            bill.setSeller(iAppDB.getAllSellers().get(currentSeller));
        }



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
        bill.getProductList().add(product);
        bill.setNextProductId(bill.getNextProductId() + 1);
        bill.setAmountPrice(bill.getAmountPrice() + product.getPrice());

        iAppDB.updateBill(bill);

        logger.info("Method addProduct done");
        return bill;
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

        if (billId < 0) {
            logger.info("Bill id is less than 0.");
            return null;
        }

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

        if (iAppDB.getAllSellers().size() == 0 || iAppDB.getAllBills().size() == 0) {
            logger.warning("Sellers or bills are null");
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
            if (seller.getFullName() != null && seller.getSoldProducts() == 0) {
                if (seller.getFullName().equals(bill.getSeller().getFullName()))
                    seller.setSoldProducts(seller.getSoldProducts() + bill.getProductList().size());
            }
        }

        logger.info("Seller sold products were calculated.");
        return seller;
    }

    @Override
    public SalesStatistic doSomeStatisticStuff() {

        SalesStatistic salesStatistic = new SalesStatistic();

        if (iAppDB.getAllSellers().size() == 0 || iAppDB.getAllBills().size() == 0) {
            logger.warning("Sry, we haven't any bills or sellers!");
            return null;
        }

        salesStatistic.setMaxBillPrice(findMaxPriceBill(iAppDB));
        salesStatistic.setMinBillPrice(findMinPriceBill(iAppDB));
        salesStatistic.setSoldProducts(calculateSumOfSoldProducts(iAppDB));
        salesStatistic.setBestSalesMan(getTopOfSalesman());

        logger.info("Method doStatisticStuff was done.");
        return salesStatistic;
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
    public void turnOnDatabaseLogger() {
        iAppDB.getLogger().setLevel(Level.INFO);
    }

    @Override
    public void turnOnTerminalLogger() {
        logger.setLevel(Level.INFO);
    }
}
