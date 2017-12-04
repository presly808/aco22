package task_java8.comparators;

import task_java8.model.User;

import java.util.Comparator;

public class SalaryUserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return (int) (o2.getSalary() - o1.getSalary());
    }
}