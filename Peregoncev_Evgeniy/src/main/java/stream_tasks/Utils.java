package stream_tasks;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ENIAC on 06.12.2017.
 */

public class Utils {

    public static List<User> SortedByAgeSexCity(List<User> userArrayList,
                                                String sex,
                                                String city,
                                                int ageFrom,
                                                int ageTo) {

        return userArrayList.stream()
                .filter(user -> (user.getSex().equals(sex) && (user.getSity().equals(city))))
                .filter(user -> user.getAge() <= ageTo && user.getAge() >= ageFrom)
                .sorted(new ComparatorByAge())
                .collect(Collectors.toList());
    }

    public static List<User> TopBySalary(List<User> userArrayList,
                                         int count) {

        return userArrayList.stream()
                .sorted(new ComparatorBySalary())
                .limit(count)
                .collect(Collectors.toList());
    }

    public static double citySalaries(List<User> userArrayList,
                                      String city,
                                      char firstLetter) {

        return userArrayList.stream()
                .filter(user -> user.getSity().equals(city))
                .filter(user -> user.getName().charAt(0) == firstLetter)
                .mapToDouble(User::getSalary).sum();
    }

    public static long howManyInCity(List<User> userArrayList,
                                     String city) {

        return userArrayList.stream()
                .filter(user -> user.getSity().equals(city))
                .count();
    }

    public static double salaryInCityBySex(List<User> userArrayList,
                                           String city,
                                           String sex) {

        return userArrayList.stream()
                .filter(user -> user.getSity().equals(city))
                .filter(user -> user.getSex().equals(sex))
                .mapToDouble(User::getSalary).average().getAsDouble();
    }

    public static List<User> bonus(List<User> userArrayList,
                                   double bonusPercent) {

        return userArrayList.stream()
                .peek(user -> user.setSalary(user.getSalary() + user.getSalary() * bonusPercent))
                .collect(Collectors.toList());
    }

    public static Map<String, Double> sortByDep(List<User> userArrayList) {

        Map<String, Double> map = userArrayList.stream()
                .collect(Collectors.groupingBy(User::getDepartment, Collectors.summingDouble(User::getSalary)));

        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap
                        (Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, LinkedHashMap::new));
    }

}
