package streamTasks;

import java.util.*;

/**
 * Created by ENIAC on 03.12.2017.
 */
public class Stream {

    public static void main(String[] args) {

        List<User> userArrayList = UserArrayList.userArrayList();

        Utils.kievWomenSortedByAge(userArrayList);

        Utils.threeTopBySalary(userArrayList);

        Utils.odessaSalaries(userArrayList);

        Utils.howManyInKiev(userArrayList);

        Utils.salaryInKiev(userArrayList);

        Utils.sortByDep(userArrayList);

        Utils.bonus(userArrayList);

    }

}
