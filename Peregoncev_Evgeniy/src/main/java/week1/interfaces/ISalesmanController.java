package week1.interfaces;

import week1.models.Department;
import week1.models.Salesman;

public interface ISalesmanController {

    double calculateBillPartFromSallary(Salesman current);

    double calculateSallary(Department department, Salesman current);
    

    }
