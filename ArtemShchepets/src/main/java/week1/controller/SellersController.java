package week1.controller;

import week1.exceptions.UnableToCalcucateDeptCostsException;
import week1.exceptions.UnableToCalculateBillIncomeException;
import week1.exceptions.UnableToCalculateSellerSalaryException;
import week1.model.IncomeExpenses;
import week1.model.Seller;

import java.util.List;

public class SellersController {

    private static final double PERCENT_FROM_BILL = 0.2;
    private static final double PERCENT_FROM_SUBSELLER = 0.3;

    public double calculateAllSellerSalary( Seller seller) throws UnableToCalculateSellerSalaryException {

        if (seller == null) throw new UnableToCalculateSellerSalaryException("Seller is null");

        List<Seller> subsellers = seller.getSubsellers();

        double salary = calculateOwnSellerSalary(seller);
        if (!subsellers.isEmpty()) {
            for (int i = 0; i < subsellers.size(); i++) {
                salary += calculateAllSellerSalary(subsellers.get(i)) * PERCENT_FROM_SUBSELLER;
            }
        }

        seller.setSalary(salary);
        return salary;
    }

    private double calculateOwnSellerSalary(Seller seller) {

        double salary = 0;

        for (int i = 0; i < seller.getBills().size(); i++) {
            salary += seller.getBills().get(i).getAmountPrice() * PERCENT_FROM_BILL;
        }

        return salary;
    }

    public double calculateDepartamentCosts(Seller rootSeller) throws UnableToCalcucateDeptCostsException {

        if (rootSeller == null) throw new UnableToCalcucateDeptCostsException("Root seller is null!");

        List<Seller> subsellers = rootSeller.getSubsellers();

        double salary = calculateOwnSellerSalary(rootSeller);

        if (!subsellers.isEmpty()) {
            for (int i = 0; i < subsellers.size(); i++) {

                salary += calculateDepartamentCosts(subsellers.get(i));
            }
        }

        return salary;
    }

    public IncomeExpenses calculateIncomeAndExpenses(Seller mainSeller) throws UnableToCalculateBillIncomeException, UnableToCalcucateDeptCostsException {

        IncomeExpenses incomeExpenses = new IncomeExpenses();

        incomeExpenses.setExpenses(calculateDepartamentCosts(mainSeller));
        incomeExpenses.setIncome(calculateIncomeFromBills(mainSeller));

        return incomeExpenses;
    }

    private double calculateIncomeFromBills(Seller mainSeller) throws UnableToCalculateBillIncomeException {
        if (mainSeller == null) throw new UnableToCalculateBillIncomeException("Main seller is null!");

        List<Seller> subsellers = mainSeller.getSubsellers();

        double salary = calculateIncomeForDepartmentFromOneSeller(mainSeller);

        if (!subsellers.isEmpty()) {
            for (int i = 0; i < subsellers.size(); i++) {

                salary += calculateIncomeFromBills(subsellers.get(i));
            }
        }

        return salary;
    }

    private double calculateIncomeForDepartmentFromOneSeller(Seller mainSeller) {

        double incomeFromSeller = 0;

        for (int i = 0; i < mainSeller.getBills().size(); i++) {
            incomeFromSeller += mainSeller.getBills().get(i).getAmountPrice();
        }

        return incomeFromSeller;
    }
}
