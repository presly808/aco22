package controllers;

import interfaces.ISalesmanController;
import models.Bill;
import models.Product;
import models.Salesman;

import java.util.ArrayList;
import java.util.List;

public class SalesmanController implements ISalesmanController {

    private static final double DEFAULT_SALARY = 4000;
    private static final double COEFICIENT = 0.15;
    private static final double MAIN_COEFICIENT = 0.2;

    public double calculateSalaryForWorker(Salesman salesman) {
        if (salesman == null) {
            throw new IllegalArgumentException(salesman + "is null");
        }

        List<Bill> salesmanBills = salesman.getBills();
        List<Salesman> salesmanList = salesman.getSalesmanList();

        double salary = DEFAULT_SALARY;
        if (salesmanList == null || salesmanBills == null) {
            salesman.setSalary(salary);
            return salary;
        }

        for (Salesman salesmanL : salesmanList) {
            salary += getProfitForListOfBills(salesmanL.getBills());
        }

        salary = salary + getProfitForOwnBills(salesman);
        salesman.setSalary(salary);

        return salary;
    }

    public double calculateDepartmentCostsToSalary(List<Salesman> salesmanList) {

        if (salesmanList == null) {
            return 0;
        }

        double cost = 0;
        for (Salesman salesman : salesmanList) {
            cost += calculateSalaryForWorker(salesman);
        }

        return cost;
    }

    public List<Salesman> getListOfSubordinators(Salesman salesman, List<Salesman> salesmanList){
        salesmanList.add(salesman);
        List<Salesman> subordinators = salesman.getSalesmanList();

        if (subordinators == null) {
            return salesmanList;
        }

        for (Salesman subordinator : subordinators) {
            if (subordinator.getSalesmanList() != null) {
                getListOfSubordinators(subordinator.getSalesmanList().iterator().next(), salesmanList);
            } else {
                getListOfSubordinators(subordinator, salesmanList);
            }
        }

        return salesmanList;
    }

    public double getProfitForOwnBills(Salesman salesman){
        return getProfitForListOfBills(salesman.getBills()) * MAIN_COEFICIENT;
    }

    public double getProfitForListOfBills(List<Bill> billList){

        if (billList == null) {
            return 0;
        }

        double billPrice = 0;
        for (Bill bill : billList) {
            billPrice += getBillProfit(bill);
        }
        return billPrice;
    }

    public double getBillProfit(Bill bill){
        return  bill.getProducts().stream().mapToDouble(Product::getPrice).sum() * COEFICIENT;
    }
}
