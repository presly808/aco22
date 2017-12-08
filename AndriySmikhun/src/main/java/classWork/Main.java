package classWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        User user1 = new User(1,"Olga", "women",27,2500,"Kyiv","Account");
        User user2 = new User(2,"Oleksandr", "man",32,5000,"Lviv","IT");
        User user3 = new User(3,"Andrii","men",38,3700,"Odessa","Marketing");
        User user4 = new User(4,"Sergii","men",47,4000,"Kharkiv","IT");
        User user5 = new User(5,"Oleg","women",29,3800,"Dnipro","Marketing");
        User user6 = new User(6,"Ira","women",31,4500,"Odessa","Account");
        User user7 = new User(7,"Natalia","women",39,4800,"Ternopil","Marketing");

        Worker worker = new Worker(Arrays.asList(user1,user2,user3,user4,user5,user6,user7));
        List<Worker> tmp = new ArrayList<>();

        worker.topThree();



    }
}
