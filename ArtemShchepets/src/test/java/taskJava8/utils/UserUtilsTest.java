package taskJava8.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import taskJava8.model.User;

import java.util.ArrayList;
import java.util.List;

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

}