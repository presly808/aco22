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

    public static final int SALASMAN_QUANTITY = 100;
    public static final int SALESMAN_INDEX = 50;
    private List<Salesman> list;

    @Before
    public void prepareData(){
        list = new ArrayList<>();
        this.list = generateSalesManList
                (SALASMAN_QUANTITY);
    }

    @Test
    public void calculateSalaryForWorker() throws Exception {
        ISalesmanController iSalesmanController =
                                    new SalesmanController();
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