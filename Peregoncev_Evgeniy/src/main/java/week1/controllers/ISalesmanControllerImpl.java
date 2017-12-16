package week1.controllers;

import week1.interfaces.ISalesmanController;
import week1.models.Department;
import week1.models.Salesman;

import java.util.List;

public class ISalesmanControllerImpl implements ISalesmanController {

    private final double percentageOfSales = 0.05;
    private final double percentageOfSubs = 0.02;

    @Override
    public double calculateBillPartFromSallary(Salesman current) {

        return current.getSumOfClosedBills() * percentageOfSales;
    }

    @Override
    public double calculateSallary(Department department, Salesman current) {
        double salary = calculateBillPartFromSallary(current);
        List<Salesman> subSalesmans = current.getSubsalesmans();
        if (!subSalesmans.isEmpty()) {

            for (Salesman subSalesman : subSalesmans) {
                salary += calculateSallary(department, subSalesman) * percentageOfSubs;
            }
        }

        department.setMoneyIncome(department.getMoneyIncome() + current.getSumOfClosedBills());
        department.setMoneyToPay(department.getMoneyToPay() + salary);

        current.setSalary(salary);

        return salary;
    }

}
