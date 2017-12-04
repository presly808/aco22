package controllers;

import exceptions.InvalidSalesmanException;
import exceptions.UnableToCalculateSalaryException;
import exceptions.UnableToGetSubordinatorsException;
import interfaces.ISalesmanController;
import models.Bill;
import models.Product;
import models.Salesman;

import java.util.List;

public class SalesmanController implements ISalesmanController {

    private static final double DEFAULT_SALARY = 4000;
    private static final double COEFICIENT = 0.15;
    private static final double MAIN_COEFICIENT = 0.2;

    public double calculateSalaryForWorker(Salesman salesman) throws UnableToCalculateSalaryException {
        if (salesman == null) {
            throw new UnableToCalculateSalaryException(salesman + "is null");
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

    public double calculateDepartmentCostsToSalary(List<Salesman> salesmanList)
                                    throws UnableToCalculateSalaryException, InvalidSalesmanException {

        if (salesmanList == null || salesmanList.isEmpty()) {
            throw new InvalidSalesmanException("Salesman list is empty");
        }

        double cost = 0;
        for (Salesman salesman : salesmanList) {
            cost += calculateSalaryForWorker(salesman);
        }

        return cost;
    }

    public List<Salesman> getListOfSubordinators(Salesman salesman, List<Salesman> salesmanList)
                                                            throws UnableToGetSubordinatorsException {
        if (salesman == null) {
            throw new UnableToGetSubordinatorsException("Unable to get subordinators for " + salesman);
        }

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
        double profitForListOfBills = 0;
        try {
            profitForListOfBills = getProfitForListOfBills(salesman.getBills());
        } catch (UnableToCalculateSalaryException e){

        }
        return profitForListOfBills * MAIN_COEFICIENT;
    }

    public double getProfitForListOfBills(List<Bill> billList) throws UnableToCalculateSalaryException {

        if (billList == null) {
            throw new UnableToCalculateSalaryException("Bill list not init");
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
