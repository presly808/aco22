package week3.model;

import week3.controller.Util;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Salesman sales1 = new Salesman(7,"sss1","ddd4", "Fullname1", Arrays.asList());
        Salesman sales2 = new Salesman(8,"sss2","ddd6", "Fullname2", Arrays.asList());

        Salesman sales3 = new Salesman(9,"sss3","ddd4", "Fullname3", Arrays.asList());
        Salesman sales4 = new Salesman(4,"sss4","ddd5", "Fullname4", Arrays.asList());

        Salesman manager  = new Salesman( 1, "Manager", "30FF76v", "Manager1", Arrays.asList(sales1,sales2) );
        Salesman manager2 = new Salesman(2,"Man2", "1234567", "Manager2",Arrays.asList(sales3,sales4));
        Salesman director = new Salesman(5,"Director", "dir123", "Starshoy", Arrays.asList(manager,manager2));

        System.out.println(manager);
        System.out.println(Util.searchMan(director));


    }
}
