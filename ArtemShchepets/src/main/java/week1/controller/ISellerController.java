package week1.controller;

import week1.exceptions.UnableToCalcucateDeptCostsException;
import week1.exceptions.UnableToCalculateBillIncomeException;
import week1.exceptions.UnableToCalculateSellerSalaryException;
import week1.model.IncomeExpenses;
import week1.model.Seller;

public interface ISellerController {

    double calculateAllSellerSalary(Seller seller) throws UnableToCalculateSellerSalaryException;

    double calculateDepartamentCosts(Seller seller) throws UnableToCalcucateDeptCostsException;

    IncomeExpenses calculateIncomeAndExpenses(Seller mainSeller) throws UnableToCalculateBillIncomeException, UnableToCalcucateDeptCostsException;

}
