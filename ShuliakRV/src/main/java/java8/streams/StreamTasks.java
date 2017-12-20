package java8.streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTasks {

    private List<User> users;

    public StreamTasks(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<User> findUsersBySexAgeCity(int ageFrom, int ageTo,
                                            String sex, String city) {

        return users.stream().filter(u -> u.getAge() > ageFrom &&
                u.getAge() < ageTo).
                filter(u -> u.getSex().equals(sex)).filter(u ->
                u.getCity().equals(city)).sorted(Comparator.
                comparingInt(User::getAge))
                .collect(Collectors.toList());

    }

    public List<User> findFirstNUsersSortedBySallery(int n) {

        return users.stream().
                sorted((u1, u2) -> (int) (u1.getSalary() - u2.getSalary())).
                skip(users.size() - n).
                collect(Collectors.toList());

    }

    public double sumOfUsersSalary(String city, String startsWith) {

        return users.stream().filter(u -> u.getCity().equals(city) &&
                u.getName().startsWith(startsWith)).
                mapToDouble(u -> u.getSalary()).sum();

    }

    private void addSalary(User user, double percent) {
        user.setSalary(user.getSalary() * (100+percent) / 100);
    }

    public void addSalaryPercent(double percent) {
        users.stream().peek(u -> addSalary(u, percent)).
                forEach(u -> System.out.
                        println("id = " + u.getId() + "," + "name = " +
                                u.getName() + "," +
                                "salary = " + u.getSalary()));
    }

    public long countUsersLiveInCity(String city) {
        return users.stream().filter(u -> u.getCity().equals(city)).count();
    }

    public double avarageSalaryUsersLiveInCity(String city) {

        return users.stream().filter(u -> u.getCity().equals(city)).
                mapToDouble(u -> u.getSalary()).average().getAsDouble();
    }

    public double sumSallaryGroupedByDepartment(String department) {
        return users.stream().filter(u -> u.getDepartment().
                equals(department)).mapToDouble(u -> u.getSalary()).sum();
    }

}
