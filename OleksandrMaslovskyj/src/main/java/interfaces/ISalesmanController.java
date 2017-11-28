package interfaces;


import models.Salesman;

import java.util.List;

public interface ISalesmanController {

    int calculateSalaryForWorker(Salesman salesman);

    double calculateDepartmentCostsToSalary(List<Salesman> salesmanList);

}
