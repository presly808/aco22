package utilstests;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
* Input Stream of Users(id,name,sex,salary,city,department)

** find users(women) that live in kiev and age > 25 age < 30 sorted by age
** get first three top users(the most high salary) sorted by salary
** sum all salaries of Odessa's users, user name should start with letter 'A'
** Add 5%(from salary) to every user, print with next patter 'id=,name=,salary='
** How many users do live in Kiev?
** Avarage salary in Kiev(Men)
** get sum of salaries grouped by department and sorted by sum
*
* */
public class Java8Test {

    public List<User> womenThatLiveInkievAndAge(List<User> userList,
                                                String type, int minAge,
                                                int maxAge, String city){
        List<User> filteredUsers = userList.stream().filter(
                (user ->
                        user.getType().equals(type)
                        && user.getCity().equals(city)
                        && user.getAge() > minAge
                        && user.getAge() < maxAge)).
                collect(Collectors.toList());
        filteredUsers.sort(Comparator.comparingInt(User::getAge));
        return filteredUsers;
    }

    public List<User> getTopUsersThatSortedBySalary(List<User> userList, int usersValue){
        userList.sort((o1, o2) -> (int) (o1.getSalary() - o2.getSalary()));
        return userList.subList(0, usersValue - 1);
    }

    public double calculateSalaryByCityAndNameStartedFrom(List<User> userList, String city, char name){

        List<User> filteredList = userList.stream().filter((user)->
                            user.getCity().equals(city)
                            && user.getName().startsWith(String.valueOf(name))).
                collect(Collectors.toList());

        return filteredList.stream().mapToDouble(User::getSalary).sum();
    }

    public List<User> getListOfUsersThatGroupedByCityAndSortedBySalary(List<User> userList,
                                                                        String city, String department){
        Map<User, List<User>> collect = userList.stream().collect(
                                        Collectors.groupingBy(Function.identity(), Collectors.toList()));

        return null;
    }

}

class User{

    private String type;
    private int age;
    private String city;
    private double salary;
    private String name;
    private String department;

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}

