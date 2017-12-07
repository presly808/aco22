package java8;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class User {

    private int id;
    private int age;
    private String name;
    private char sex;
    private int salary;
    private String city;
    private String department;

    public User(int id, int age, String name, char sex, int salary, String city, String department) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.salary = salary;
        this.city = city;
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    // 1. ** find users(women) that live in kiev and age > 25 age < 30 sorted by age
    public static List<User> findWomenFromKievBeetwen25and30SortedByAge(List<User> users){
        return users.stream().filter(u -> u.sex == 'F')
                             .filter(u -> "Kiev".equals(u.city))
                             .filter(u -> u.age > 25 && u.age < 30)
                             .sorted((u1 , u2) -> u1.age - u2.age)
                             .collect(Collectors.toList());
    }

    // 2. ** get first three top users(the most high salary) sorted by salary
    public static List<User> getFirst3TopUserHighSalary(List<User> users){
        return users.stream().sorted((u1, u2) -> u2.salary - u1.salary)
                             .limit(3)
                             .collect(Collectors.toList());
    }

    // 3. ** sum all salaries of Odessa's users, user name should start with letter 'A'
    public static int getSumAllSalariesOdessaUsersWithNameBeginFromA(List<User> users){
        return users.stream().filter(u -> u.name.substring(0,1).equals("A"))
                             .filter(u -> "Odessa".equals(u.city))
                             .mapToInt(User::getSalary).sum();
    }

    // 4. ** Add 5%(from salary) to every user, print with next patter 'id=,name=,salary='
    public static String add5PercentToSalary(List<User> users){
        return users.stream().peek(u -> u.salary += u.salary * 0.05)
                    .map(user -> "id="+user.id + ",name=" + user.name + ",salary=" + user.salary)
                    .collect(Collectors.joining("\n"));
    }

    // 5. ** How many users do live in Kiev?
    public static int getHowManyUsersLiveInKiev(List<User> users){
       return (int) users.stream().filter(u -> "Kiev".equals(u.city))
                                  .count();
    }

    // 6. ** Avarage salary in Kiev(Men)
    public static int getAvarageSalaryInKiev(List<User> users){
        return (int) users.stream().filter(u -> "Kiev".equals(u.city) && u.sex == 'M')
                                   .mapToInt(u -> u.salary)
                                   .average()
                                   .getAsDouble();
    }

    // 7. ** get sum of salaries grouped by department and sorted by sum
    public static Map<String, Integer> getSumOfSalariesGroupedByDepartment(List<User> users){
        return users.stream().collect(Collectors.groupingBy(User::getDepartment, Collectors.summingInt(User::getSalary)));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", salary=" + salary +
                ", city='" + city + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
