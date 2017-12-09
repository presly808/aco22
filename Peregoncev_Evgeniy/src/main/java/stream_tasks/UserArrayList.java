package stream_tasks;

import java.util.ArrayList;

/**
 * Created by ENIAC on 06.12.2017.
 */

public class UserArrayList {

    public static ArrayList<User> userArrayList() {

        ArrayList<User> userArrayList = new ArrayList<>();

        userArrayList.add(new User(0, 29, 120, "Alla", "female", "Kiev", "AO"));
        userArrayList.add(new User(1, 26, 115, "Jora", "male", "Kiev", "OA"));
        userArrayList.add(new User(2, 27, 150, "Anna", "female", "Odessa", "OAO"));
        userArrayList.add(new User(3, 28, 475, "Anton", "male", "Odessa", "OA"));
        userArrayList.add(new User(4, 29, 100, "Galla", "female", "Odessa", "OA"));
        userArrayList.add(new User(5, 40, 320, "Igor", "male", "Kiev", "OAO"));
        userArrayList.add(new User(6, 25, 100, "Vanessa", "female", "Kiev", "OA"));
        userArrayList.add(new User(7, 27, 425, "Nastia", "female", "Kiev", "AO"));
        userArrayList.add(new User(8, 26, 150, "Nadia", "female", "Kiev", "OAO"));

        return userArrayList;
    }

}
