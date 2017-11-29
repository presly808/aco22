package logictests;

import controllers.SalesmanController;
import interfaces.ISalesmanController;
import models.Bill;
import models.Product;
import models.Salesman;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.SalaryGenerator;
import utils.StringGenerator;

import java.util.ArrayList;
import java.util.List;


public class SalasManControllerTest {

    private static final int SALASMAN_QUANTITY = 100;
    private static final int SALESMAN_INDEX = 50;
    public static final double COEFICIENT = 0.15;

    private List<Salesman> list;
    private ISalesmanController iSalesmanController;
    private Salesman salesman;


    @Before
    public void prepareData(){
        list = new ArrayList<>();
        this.list = generateSalesManList
                (SALASMAN_QUANTITY);

         iSalesmanController = new SalesmanController();
         salesman = new Salesman(StringGenerator.generateName(),
                StringGenerator.generateName());
    }

    @Test
    public void calculateSalaryForWorker() throws Exception {
        Salesman salesman = list.get(SALESMAN_INDEX);
        double salary = iSalesmanController.
                                calculateSalaryForWorker(salesman);
        Assert.assertTrue(salesman.getSalary() == salary);
    }

    @Test
    public void calculateDepartmentCostsToSalary() throws Exception {
        ISalesmanController iSalesmanController =
                                    new SalesmanController();
        List<Salesman> salesmanList = generateSalesManList(SALASMAN_QUANTITY);

        double calculatedDepartmentCost =
                iSalesmanController.calculateDepartmentCostsToSalary(salesmanList);
        Assert.assertNotNull(calculatedDepartmentCost);
        Assert.assertTrue(calculatedDepartmentCost == calculateCost(salesmanList));
    }

    @Test
    public void testGetListOfSubordinators(){
        List<Salesman> salesmanList =
                iSalesmanController.getListOfSubordinators(salesman, new ArrayList<>());
        Assert.assertTrue(salesmanList.size() == 1);

        salesman.setSalesmanList(generateSalesManList(2));
        salesmanList = iSalesmanController.getListOfSubordinators(salesman, new ArrayList<>());
        Assert.assertTrue(salesmanList.size() == 3);
    }

    @Test
    public void testGetProfitForOwnBills(){
        salesman.setBills(null);
        double salary = iSalesmanController.getProfitForOwnBills(salesman);
        Assert.assertTrue(salary == 0);

        salesman.setBills(generateBillList());
        salary = iSalesmanController.getProfitForOwnBills(salesman);
        Assert.assertFalse(salary == 0);
    }

    @Test
    public void testGetProfitForListOfBills(){
        double profitForListOfBills = iSalesmanController.getProfitForListOfBills(generateBillList());
        Assert.assertNotNull(profitForListOfBills);
    }

    @Test
    public void testGetBillProfit(){
        Bill bill = new Bill();
        double billProfit = iSalesmanController.getBillProfit(bill);
        Assert.assertTrue(billProfit == 0);

        int price = 4000;
        bill.setProducts(new Product(StringGenerator.generateName(),
                price));
        billProfit = iSalesmanController.getBillProfit(bill);
        Assert.assertTrue(billProfit == price * COEFICIENT);
    }

    private List<Salesman> generateSalesManList(int quantity){
        List<Salesman> list = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Salesman salesman = new Salesman(
                    StringGenerator.generateName(),
                    StringGenerator.generateName());
            salesman.setBills(generateBillList());
            list.add(salesman);
        }
        return list;
    }

    private List<Bill> generateBillList(){
        List<Bill> billList = new ArrayList<>();

        for (int i = 0; i < SALASMAN_QUANTITY; i++) {
            Bill bill = new Bill();
            bill.setProducts(new Product(StringGenerator.generateName(),
                    (SalaryGenerator.generatePrice())));
            billList.add(bill);
        }
        return billList;
    }

    private double calculateCost(List<Salesman> salesmanList){
        return salesmanList.stream().mapToDouble(Salesman::getSalary).sum();
    }

}