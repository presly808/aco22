package ua.artcode.stream_tasks;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Tasks {

    private List<User> users;

    public Tasks(List<User> users) {
        this.users = users;
    }

    // find users(women) that live in kiev and age > 25 age < 30 sorted by age
    public List<User> findUsersBySexCityAge(String sex, String city, int ageFrom, int ageTo) {

        return users.stream()
                .filter(u -> u.getSex().equals(sex))
                .filter(u -> u.getCity().equalsIgnoreCase(city))
                .filter(u -> u.getAge() >= ageFrom && u.getAge() <= ageTo)
                .sorted(Comparator.comparingInt(User::getAge))
                .collect(Collectors.toList());
    }

    // get first three top users(the most high salary) sorted by salary
    public List<User> getTop(int count) {

        return users.stream()
                .sorted(Comparator.comparingDouble(User::getSalary))
                .skip(users.size() - count)
                .collect(Collectors.toList());
    }

    // sum all salaries of Odessa's users, user name should start with letter 'A'
    public double sumAllSalaries(String city, String firstSymbolOfName) {

        return users.stream()
                .filter(u -> u.getCity().equals(city) &&
                        u.getName().startsWith(firstSymbolOfName))
                .mapToDouble(User::getSalary).sum();
    }

    // Add 5%(from salary) to every user, print with next patter 'id=,name=,salary='
    public void addSalaryAndPrint(int percent) {

        users.stream()
                .peek(u -> UserUtils.increaseSalary(u, percent))
                .forEach(u -> System.out.printf("id = %d, name = %s, salary = %.2f \n",
                        u.getId(), u.getName(), u.getSalary()));
    }

    // How many users do live in Kiev?
    public int howManyLiveIn(String city) {

        return (int) users.stream().filter(u -> u.getCity().equals(city)).count();
    }

    // Avarage salary in Kiev(Men)
    public double averageSalaryIn(String city) {

        return users.stream()
                .filter(u -> u.getCity().equals(city))
                .mapToDouble(User::getSalary)
                .average()
                .getAsDouble();
    }

    // get sum of salaries grouped by department and sorted by sum
    public double getSalarySumByDepartment(String department) {

        return users.stream()
                .filter(u -> u.getDepartment().equals(department))
                .mapToDouble(User::getSalary)
                .sum();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

// methods
// intermediate middle -> Stream
// map -> operation and overing -> Function body
// filter -> Predicate
// peek -> take and some operation, Consumer, no return, dont change stream
// distinct -> only unique elements
// limit -> limit by size
// skip(count)
// sorted - input Comparator

// terminal - dont generate stream
// allMatch
// anyMatch
// count
// toArray
// forEach
// findAny
// findFirst
// collect -> Stream to Collection, String
// reduce -> aggrageteFunction from many get one value
