package task_java8.comparators;

import task_java8.model.User;

import java.util.Comparator;

public class AgeUserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getAge() - o2.getAge();
    }
}
