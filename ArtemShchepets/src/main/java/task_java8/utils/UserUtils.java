package task_java8.utils;

import task_java8.comparators.AgeUserComparator;
import task_java8.comparators.SalaryUserComparator;
import task_java8.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserUtils {

    // find users(women) that live in kiev and age > 25 age < 30 sorted by age
    public static List<User> firstTask(List<User> users) {
        return users.stream().
                filter(x -> x.getSex().equals("F") && (x.getCity().equals("Kyiv"))
                        && (x.getAge() > 25 && x.getAge() < 30)).sorted(new AgeUserComparator())
                .collect(Collectors.toList());
    }

    // get first three top users(the most high salary) sorted by salary
    public static List<User> secondTask(List<User> users) {
        return users.stream().
                sorted(new SalaryUserComparator()).limit(3)
                .collect(Collectors.toList());
    }

    //** sum all salaries of Odessa's users, user name should start with letter 'A'
    public static double thirdTask(List<User> users) {

        return users.stream()
                .filter(user -> user.getCity().equals("Odessa"))
                .filter(user -> user.getName().charAt(0) == 'A').mapToDouble(User::getSalary).sum();
    }

    //** Add 5%(from salary) to every user, print with next patter 'id=,name=,salary='
    public static List<User> fourthTask(List<User> users) {
        return users.stream().
                peek(user -> user.setSalary(user.getSalary() + (user.getSalary() * 0.05)))
                .sorted(new AgeUserComparator())
                .collect(Collectors.toList());
    }

    //** How many users do live in Kiev?

    public static long fifthTask(List<User> users) {
        return users.stream().filter(user -> user.getCity().equals("Kyiv")).count();
    }

    //** Average salary in Kiev(Men)
    public static double sixthTask(List<User> users) {
        return users.stream().filter(user -> user.getSex().equals("M")
                && (user.getCity().equals("Kyiv")))
                .mapToDouble(User::getSalary).average().getAsDouble();
    }

    //** get sum of salaries grouped by department and sorted by sum

    public static Map<Integer,Double> seventhTask(List<User> users) {

        Map<Integer,Double> map =  users.stream().
                collect(Collectors
                        .groupingBy(User::getCompany, Collectors.summingDouble(User::getSalary)));

        return map.entrySet().stream().
                sorted(Map.Entry.comparingByValue())
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
    }
}
