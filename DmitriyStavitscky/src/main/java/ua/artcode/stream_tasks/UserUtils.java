package ua.artcode.stream_tasks;

public class UserUtils {

    public static void increaseSalary(User user, int percent) {
        user.setSalary(user.getSalary() + (user.getSalary() / 100) * percent);
    }
}
