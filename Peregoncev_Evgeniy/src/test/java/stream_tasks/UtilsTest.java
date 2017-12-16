package stream_tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static stream_tasks.UserArrayList.userArrayList;
import static org.junit.Assert.*;

/**
 * Created by ENIAC on 06.12.2017.
 */
public class UtilsTest {

    private List<User> testUsers;

    @Before
    public void setUp() throws Exception {
        testUsers = userArrayList();
    }

    @After
    public void tearDown() throws Exception {
        testUsers = null;
    }

    @Test
    public void SortedByAgeSexCity() throws Exception {

        List<User> expected = new ArrayList<>();

        expected.add(userArrayList().get(6));
        expected.add(userArrayList().get(8));
        expected.add(userArrayList().get(7));
        expected.add(userArrayList().get(0));

        List<User> actual = Utils.SortedByAgeSexCity(testUsers,"female","Kiev",25,30);
        assertEquals(expected, actual);
    }

    @Test
    public void TopBySalary() throws Exception {
        List<User> expected = new ArrayList<>();

        expected.add(userArrayList().get(3));
        expected.add(userArrayList().get(7));
        expected.add(userArrayList().get(5));

        List<User> actual = Utils.TopBySalary(testUsers,3);
        assertEquals(expected, actual);
    }

    @Test
    public void citySalaries() throws Exception {
        assertEquals(625.0, Utils.citySalaries(testUsers,"Odessa",'A'), 0.01);
    }

    @Test
    public void howManyInCity() throws Exception {
        assertEquals(6, Utils.howManyInCity(testUsers,"Kiev"));
    }

    @Test
    public void salaryInCityBySex() throws Exception {
        assertEquals(217.5, Utils.salaryInCityBySex(testUsers,"Kiev","male"), 0.01);
    }

    @Test
    public void bonus() throws Exception {
        assertEquals(126.0, Utils.bonus(testUsers,0.05).get(0).getSalary(), 0.01);

    }

    @Test
    public void sortByDep() throws Exception {
        Map<String, Double> expected = new HashMap<>();
        expected.put("AO", 545.0);
        expected.put("OAO", 620.0);
        expected.put("OA", 790.0);
        assertEquals(expected, Utils.sortByDep(testUsers));
    }

}