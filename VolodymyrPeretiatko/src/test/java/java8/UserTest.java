package java8;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserTest {

    private static User u1, u2, u3, u4, u5, u6, u7, u8, u9, u10;
    private static List<User> users = new ArrayList<>();

    private List<User> expected;

    @BeforeClass
    public static void testDataInit(){

        u1 = new User(1, 18,"Jhon", 'M', 1000, "Kiev", "IT");
        u2 = new User(2, 25,"Suhi", 'F', 1020, "Odessa", "IT");
        u3 = new User(3, 29,"Loli", 'F', 1020, "Kiev", "IT");
        u4 = new User(4, 35,"Bobi", 'M', 1040, "Kiev", "IT");
        u5 = new User(5, 29,"Alabama", 'M', 1050, "Kiev", "IT");
        u6 = new User(6, 31,"Alisa", 'F', 1060, "Odessa", "CEO");
        u7 = new User(7, 28,"Belisa", 'F', 1070, "Kiev", "CEO");
        u8 = new User(8, 23,"Anfisa", 'F', 1080, "Odessa", "CEO");
        u9 = new User(9, 47,"Rustam", 'M', 1090, "Odessa", "CEO");
        u10 = new User(10, 21,"Lionel", 'M', 1010, "Kiev", "CEO");

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        users.add(u6);
        users.add(u7);
        users.add(u8);
        users.add(u9);
        users.add(u10);
    }

    @Before
    public void init(){
        expected = new ArrayList<>();
    }

    @Test
    public void findWomenFromKievBeetwen25and30SortedByAgeTest(){

        expected.add(u7);
        expected.add(u3);

        List<User> actual = User.findWomenFromKievBeetwen25and30SortedByAge(users);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getFirst3TopUserHighSalaryTest(){

        expected.add(u9);
        expected.add(u8);
        expected.add(u7);

        List<User> actual = User.getFirst3TopUserHighSalary(users);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getSumAllSalariesOdessaUsersWithNameBeginFromATest(){

        int actual = User.getSumAllSalariesOdessaUsersWithNameBeginFromA(users);

        Assert.assertEquals(u6.getSalary() + u8.getSalary(), actual);

    }

    @Test
    public void add5PercentToSalaryTest(){

        String expected = "id=1,name=Jhon,salary=1337\n" +
                          "id=2,name=Suhi,salary=1365\n" +
                          "id=3,name=Loli,salary=1365\n" +
                          "id=4,name=Bobi,salary=1392\n" +
                          "id=5,name=Alabama,salary=1403\n" +
                          "id=6,name=Alisa,salary=1418\n" +
                          "id=7,name=Belisa,salary=1430\n" +
                          "id=8,name=Anfisa,salary=1444\n" +
                          "id=9,name=Rustam,salary=1459\n" +
                          "id=10,name=Lionel,salary=1351\n";

        Assert.assertFalse(expected.equals(User.add5PercentToSalary(users)));

    }
}
