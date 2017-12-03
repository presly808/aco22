package taskJava8.utils;

import taskJava8.model.User;

import java.util.ArrayList;
import java.util.List;

public class GenerateUtils {
    public static List<User> initUsers() {

        List<User> users = new ArrayList<>();

        int generatedCount = 1;

        users.add(new User( 123515, "worker1", 26, "F", 2500, "Kyiv", 1));
        users.add(new User( 123514, "worker2", 20, "M", 23456, "Lviv", 1));
        users.add(new User( 123513, "Anna", 27, "M", 4000, "Odessa", 1));
        users.add(new User( 123512, "worker111", 35, "F", 10000, "Kyiv", 1));
        users.add(new User( 123511, "worker12313", 33, "F", 25000, "Kyiv", 1));
        users.add(new User( 123510, "worker22", 15, "F", 2503, "Kyiv", 2));
        users.add(new User( 1235151, "worker0", 45, "F", 2700, "Kyiv", 2));
        users.add(new User( 1235152, "Vasya", 18, "M", 3500, "Odessa", 3));
        users.add(new User( 1235153, "worker111", 37, "F", 4500, "Kyiv", 4));
        users.add(new User( 1235154, "worker567", 30, "F", 5500, "Lviv", 4));
        users.add(new User( 1235155, "worker11233", 31, "M", 6500, "Kyiv", 4));
        users.add(new User( 1235156, "Olya", 28, "F", 7500, "Odessa", 4));
        users.add(new User( 1235157, "worker101", 29,"F", 8500, "Kyiv", 1));
        users.add(new User( 1235158, "worker10001", 20, "M", 9500, "Kyiv", 1));
        users.add(new User( 1235159, "Alex", 20, "M", 9300, "Odessa", 1));

        return users;
    }
}
