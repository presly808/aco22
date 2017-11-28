package interfaces;


import models.Salesman;

import java.util.List;

public interface ISalesmanController {

    double calculateSalaryForWorker(Salesman salesman);

    double calculateDepartmentCostsToSalary(List<Salesman> salesmanList);

}
