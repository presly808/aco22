package ua.artcode.stream_tasks;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TasksTest {

    private Tasks task = new Tasks(new ArrayList<>());

    private User user1 = new User("Dima", 1, 1, 1000, "man", "Kiev", "first");
    private User user2 = new User("Marina", 5, 2, 5000, "woman", "Kiev", "second");
    private User user3 = new User("Ivan", 10, 3, 10_000, "man", "Odessa", "first");
    private User user4 = new User("Kolia", 15, 4, 15_000, "man", "Odessa", "second");
    private User user5 = new User("Sofa", 20, 5, 20_000, "woman", "Kiev", "first");
    private User user6 = new User("Kila", 25, 6, 25_000, "man", "Kiev", "second");
    private User user7 = new User("Kira", 30, 7, 30_000, "woman", "Odessa", "first");
    private User user8 = new User("Lapa", 35, 8, 35_000, "woman", "Odessa", "second");
    private User user9 = new User("Rana", 40, 9, 40_000, "man", "Kiev", "first");
    private User user10 = new User("Motya", 45, 10, 45_000, "woman", "Kiev", "second");

    private List<User> expected;
    private List<User> actual;

    @Before
    public void before() {

        task.getUsers().add(user1);
        task.getUsers().add(user2);
        task.getUsers().add(user3);
        task.getUsers().add(user4);
        task.getUsers().add(user5);
        task.getUsers().add(user6);
        task.getUsers().add(user7);
        task.getUsers().add(user8);
        task.getUsers().add(user9);
        task.getUsers().add(user10);

        expected = new ArrayList<>();
        actual = new ArrayList<>();

    }

    // find users(women) that live in kiev and age > 25 age < 30 sorted by age
    @Test
    public void findUsersBySexCityAge() throws Exception {

        actual = task.findUsersBySexCityAge("woman", "Kiev", 5, 30);

        expected.add(user2);
        expected.add(user5);

        assertEquals(expected, actual);
    }

    // get first three top users(the most high salary) sorted by salary
    @Test
    public void getTop() throws Exception {

        actual = task.getTop(3);

        expected.add(user8);
        expected.add(user9);
        expected.add(user10);

        assertEquals(expected, actual);
    }

    // sum all salaries of Odessa's users, user name should start with letter 'A'
    @Test
    public void sumAllSalaries() throws Exception {

        double actualSalary = task.sumAllSalaries("Odessa", "K");

        double expectedSalary = user4.getSalary() + user7.getSalary();

        assertEquals(expectedSalary, actualSalary, 1);
    }

    // Add 5%(from salary) to every user, print with next patter 'id=,name=,salary='
    @Test
    public void addSalaryAndPrint() throws Exception {

        System.out.println("Test: add 5% salary");
        task.addSalaryAndPrint(5);

        assertEquals(task.getUsers().get(0).getSalary(), 1050, 1);
    }

    // How many users do live in Kiev?
    @Test
    public void howManyLiveIn() throws Exception {

        assertEquals(6, task.howManyLiveIn("Kiev"));
    }

    // Avarage salary in Kiev(Men)
    @Test
    public void averageSalaryIn() throws Exception {

        assertEquals(22500, task.averageSalaryIn("Odessa"), 1);
    }

    // get sum of salaries grouped by department and sorted by sum
    @Test
    public void getSalarySumByDepartment() throws Exception {

        assertEquals(101_000, task.getSalarySumByDepartment("first"), 1);
    }
}