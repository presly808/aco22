package interfaces;

import exceptions.InvalidSalesmanException;
import exceptions.UnableToCalculateSalaryException;
import exceptions.UnableToGetSubordinatorsException;
import models.Bill;
import models.Salesman;

import java.util.List;

public interface ISalesmanController {

    double calculateSalaryForWorker(Salesman salesman)
                        throws UnableToCalculateSalaryException;

    double calculateDepartmentCostsToSalary(List<Salesman> salesmanList)
            throws UnableToCalculateSalaryException, InvalidSalesmanException;

    List<Salesman> getListOfSubordinators(Salesman salesman, List<Salesman> salesmanList)
                                                    throws UnableToGetSubordinatorsException;

    double getProfitForOwnBills(Salesman salesman) throws UnableToCalculateSalaryException;

    double getProfitForListOfBills(List<Bill> billList)
                                throws UnableToCalculateSalaryException;

    double getBillProfit(Bill bill);

}
