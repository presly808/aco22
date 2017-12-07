package hw1.controller;

import hw1.model.*;
import hw1.reports.IncomeExpensesReport;
import hw1.utils.*;

import java.util.*;


public class Terminal implements ITerminal {

    private IAppDB appDB = new IAppDBImpl();
    private Salesman currentUser;

    @Override
    public Double getSalesmanSallary(Salesman salesman, Date startDate, Date endDate) {

        //Own sales
        List<Salesman> salesmen = new ArrayList<>();
        salesmen.add(salesman);

        Double ownSalesSalary = getSalesAmount(salesmen, startDate, endDate);

        //Sub sales
        salesmen = Salesman.getAllSubSalesmen(salesman);
        salesmen.remove(salesman);

        Double subSalesSalary = getSalesAmount(salesmen, startDate, endDate);

        return ownSalesSalary * AppBusinesLogicConst.SALLARY_PERCENT_SALES_OWN
                + subSalesSalary * AppBusinesLogicConst.SALLARY_PERCENT_SALES_SUB;
    }

    private Double getSalesAmount(List<Salesman> salesmen, Date startDate, Date endDate){

        Double salesAmount = 0.0;

        List<Bill> bills = filter(salesmen, null, startDate, endDate, new Bill.SortByDateComparator());
        for (Bill b: bills){
            salesAmount += b.getAmountPrice();
        }
        return  salesAmount;
    }

    @Override
    public List<Salesman> getSalesmen() {
        List<DBItem> items = appDB.getAll(Salesman.class);
        List<Salesman> salesmen = new ArrayList<>(items.size());
        for(DBItem i : items){
            salesmen.add((Salesman) i);
        }
        return salesmen;
    }

    @Override
    public List<Product> getProducts() {
        List<DBItem> items = appDB.getAll(Product.class);
        List<Product> products = new ArrayList<>(items.size());
        for(DBItem i : items){
            products.add((Product) i);
        }
        return products;
    }

    @Override
    public List<Bill> getBills() {
        List<DBItem> items = appDB.getAll(Bill.class);
        List<Bill> bills = new ArrayList<>(items.size());
        for(DBItem i : items){
            bills.add((Bill) i);
        }
        return bills;
    }

    @Override
    public boolean addSalesman(Salesman salesman){
        return appDB.save(salesman) != null;
    }

    @Override
    public boolean login(String name, String pass){

        List<DBItem> salesmen = appDB.getAll(Salesman.class);
        Salesman salesman;
        for (DBItem s : salesmen) {
            salesman = (Salesman) s;
            if (!name.equals(salesman.getName())){
                continue;
            }
            if (pass.equals(salesman.getPass())){
                this.currentUser = (Salesman) s;
                return true;
            }

        }
        return false;
    }

    @Override
    public Bill createBill(){
        Bill b = (Bill) appDB.create(Bill.class);
        b.setSalesman(currentUser);
        return b;
    }

    @Override
    public boolean closeAndSaveBill(Bill bill){
        bill.closeBill();
        return appDB.save(bill) != null;
    }

    @Override
    public boolean addProduct(Product p) {
        return appDB.save(p) != null;
    }

    @Override
    public Bill findBillById(int id){
        return (Bill) appDB.findById(id, Bill.class);
    }

    @Override
    public Salesman getTopNofSalesMan(){

        Map<Salesman, Double> sales = getSalesAmountBySalesman();

        Salesman topSalesman = null;
        Double amount = 0.0;
        Double currentAmount;

        List<Salesman> salesmen = getSalesmen();

        for (Salesman s : salesmen){

            currentAmount = sales.get(s);
            if (currentAmount == null){
                continue;
            }
            if (currentAmount > amount){
                amount = currentAmount;
                topSalesman = s;
            }
        }
        return topSalesman;
    }

    @Override
    public Map<Salesman, Double> getSalesAmountBySalesman(){

        HashMap<Salesman, Double> sales = new HashMap<>();
        Salesman salesman;
        List<Bill> bills = getBills();
        for (Bill b : bills){
            salesman = b.getSalesman();
            if (sales.get(salesman) == null){
                sales.put(salesman, b.getAmountPrice());
            } else {
                sales.put(salesman, sales.get(salesman) + b.getAmountPrice());
            }
        }
        return sales;
    }

    @Override
    public List<Bill> filter(List<Salesman> salesmen, List<Product> products,
                             Date startDate, Date endDate, Comparator<Bill> comparator){

        ArrayList<Bill> result = new ArrayList<>();
        Date BDate;
        List<Bill> bills = getBills();
        for(Bill b : bills){

            BDate = b.getCloseTime();
            if (startDate.compareTo(BDate) > 0 || endDate.compareTo(BDate) < 0){
                continue;
            }

            if (salesmen != null && !salesmen.contains(b.getSalesman())){
                continue;
            }

            if (products != null
                && !CollectionUtils.listContainElementsOther(b.getProducts(), products))
                    continue;


            result.add(b);

        }

        if(comparator != null && !result.isEmpty()) {result.sort(comparator); result.trimToSize();}

        return result;
    }


    public IncomeExpensesReport getIncomeExpensesByDate(Date startDate, Date endDate){

        List<Salesman> salesmen = getSalesmen();

        Double salesAmount = getSalesAmount(salesmen, startDate, endDate);
        Double salesmanSalary = 0.0;

        for (Salesman salesman : salesmen){
            salesmanSalary += getSalesmanSallary(salesman, startDate, endDate);
        }

        return new IncomeExpensesReport(startDate, endDate, salesAmount, salesmanSalary);

    }

}
