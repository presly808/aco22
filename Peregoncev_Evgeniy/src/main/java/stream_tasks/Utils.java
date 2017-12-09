package stream_tasks;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ENIAC on 06.12.2017.
 */

public class Utils {

    public static List<User> kievWomenSortedByAge(List<User> userArrayList) {

        List<User> kievWomenSortedByAge = userArrayList.stream()
                .filter(user -> (user.getSex().equals("female")) && (user.getSity().equals("Kiev")))
                .filter(user -> user.getAge() <= 30 && user.getAge() >= 25)
                .sorted(new ComparatorByAge())
                .collect(Collectors.toList());

        System.out.println("\nfind users(women) that live in kiev and age > 25 age < 30 sorted by age\n" + kievWomenSortedByAge);
        return kievWomenSortedByAge;
    }

    public static List<User> threeTopBySalary(List<User> userArrayList) {

        List<User> threeTopBySalary = userArrayList.stream()
                .sorted(new ComparatorBySalary())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\nget first three top users(the most high salary) sorted by salary\n" + threeTopBySalary);
        return threeTopBySalary;
    }

    public static double odessaSalaries(List<User> userArrayList) {

        double odessaSalaries = userArrayList.stream()
                .filter(user -> user.getSity().equals("Odessa"))
                .filter(user -> user.getName().charAt(0) == 'A')
                .collect(Collectors.summingDouble(User::getSalary));

        System.out.println("\nsum all salaries of Odessa's users, user name should start with letter 'A'\n" + odessaSalaries);
        return odessaSalaries;
    }

    public static long howManyInKiev(List<User> userArrayList) {

        long howManyInKiev = userArrayList.stream()
                .filter(user -> user.getSity().equals("Kiev"))
                .count();

        System.out.println("\nHow many users do live in Kiev?\n" + howManyInKiev);
        return howManyInKiev;
    }

    public static double salaryInKiev(List<User> userArrayList) {

        double salaryInKiev = userArrayList.stream()
                .filter(user -> user.getSity().equals("Kiev"))
                .filter(user -> user.getSex().equals("male"))
                .mapToDouble(User::getSalary).average().getAsDouble();

        System.out.println("\nAverage salary in Kiev(Men)\n" + (salaryInKiev));
        return salaryInKiev;
    }

    public static List<User> bonus(List<User> userArrayList) {

        List<User> bonus = userArrayList.stream()
                .peek(user -> user.setSalary(user.getSalary() + user.getSalary() * 0.05))
                .collect(Collectors.toList());

        System.out.println("\nAdd 5%(from salary) to every user, print with next patter 'id=,name=,salary='\n" + bonus);
        return bonus;
    }

    public static Map<String, Double> sortByDep(List<User> userArrayList) {

        Map<String, Double> map = userArrayList.stream()
                .collect(Collectors.groupingBy(User::getDepartment, Collectors.summingDouble(User::getSalary)));

        Map<String, Double> sortByDep = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> newValue, LinkedHashMap::new));

        System.out.println("\nget sum of salaries grouped by department and sorted by sum \n" + sortByDep);
        return sortByDep;
    }

}
