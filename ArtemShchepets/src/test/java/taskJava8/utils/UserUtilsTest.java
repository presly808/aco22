package taskJava8.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import taskJava8.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUtilsTest {

    private List<User> users;

    @Before
    public void setUp() throws Exception {
        users = GenerateUtils.initUsers();
    }

    @After
    public void tearDown() throws Exception {
        users = null;
    }

    @Test
    public void firstTask() throws Exception {

        List<User> expected = new ArrayList<>();
        expected.add(users.get(0));
        expected.add(users.get(12));

        Assert.assertEquals(expected, UserUtils.firstTask(users));
    }

    @Test
    public void secondTask() throws Exception {

        List<User> expected = new ArrayList<>();
        expected.add(users.get(4));
        expected.add(users.get(1));
        expected.add(users.get(3));

        Assert.assertEquals(expected, UserUtils.secondTask(users));
    }

    @Test
    public void thirdTask() throws Exception {


        Assert.assertEquals(13300, UserUtils.thirdTask(users), 0.01);
    }

    @Test
    public void fourthTask() throws Exception {
        List<User> userTest = UserUtils.fourthTask(users);

        Assert.assertEquals(2628.15, userTest.get(0).getSalary(), 0.01);
        Assert.assertEquals(2835, userTest.get(14).getSalary(), 0.01);
    }

    @Test
    public void fifthTask() throws Exception {

        Assert.assertEquals(9, UserUtils.fifthTask(users));
    }

    @Test
    public void sixthTask() throws Exception {

        Assert.assertEquals(8000, UserUtils.sixthTask(users), 0.01);
    }

    @Test
    public void seventhTask() throws Exception {

        Map<Integer, Double> testMap = new HashMap<>();

        testMap.put(3, 3500.0);
        testMap.put(2, 5203.0);
        testMap.put(4, 24000.0);
        testMap.put(1,92256.0);

        Assert.assertEquals(testMap,UserUtils.seventhTask(users));
    }


}