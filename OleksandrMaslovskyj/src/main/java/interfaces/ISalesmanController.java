package interfaces;


import models.Salesman;

import java.util.List;

public interface ISalesmanController {

    int calculateSalaryForWorker(Salesman salesman);

    int calculateDepartmentCostsToSalary(List<Salesman> salesmanList);

}
