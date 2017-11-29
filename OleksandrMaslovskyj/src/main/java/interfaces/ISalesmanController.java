package interfaces;


import models.Bill;
import models.Salesman;

import java.util.List;

public interface ISalesmanController {

    double calculateSalaryForWorker(Salesman salesman);

    double calculateDepartmentCostsToSalary(List<Salesman> salesmanList);

    List<Salesman> getListOfSubordinators(Salesman salesman, List<Salesman> salesmanList);

    double getProfitForOwnBills(Salesman salesman);

    double getProfitForListOfBills(List<Bill> billList);

    double getBillProfit(Bill bill);

}
