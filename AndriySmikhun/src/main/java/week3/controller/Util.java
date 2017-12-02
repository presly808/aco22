package week3.controller;

import week3.model.Salesman;

import java.util.List;

// todo rename,name must be clear and reasonable
public class Util {

    // do not use println???
    public static int searchMan(Salesman root) {

        int sum = 0;
        System.out.println(root.getFullName());
        // todo may slaves be null?
        List<Salesman> slaves = root.slaves;

        if (slaves == null) {
            return sum;
        }

        for (Salesman slav : slaves) {
            sum = sum + searchMan(slav);
            sum = sum + slav.getId();
            System.out.println(slav.getFullName());

        }

        return sum;
    }
}
