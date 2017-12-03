package taskJava8.utils;

import taskJava8.comparators.AgeUserComparator;
import taskJava8.comparators.SalaryUserComparator;
import taskJava8.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserUtils {

    // find users(women) that live in kiev and age > 25 age < 30 sorted by age
    public static List<User> firstTask( List<User> users) {
        return users.stream().
                filter(x -> x.getSex().equals("F") && (x.getCity().equals("Kyiv")) &&(x.getAge() > 25 && x.getAge() < 30)).sorted(new AgeUserComparator())
                .collect(Collectors.toList());
    }

    // get first three top users(the most high salary) sorted by salary
    public static List<User> secondTask(List<User> users) {
        return users.stream().sorted(new SalaryUserComparator()).limit(3).collect(Collectors.toList());
    }

    //** sum all salaries of Odessa's users, user name should start with letter 'A'
    public static double thirdTask(List<User> users) {
        return 0;
    }
}
