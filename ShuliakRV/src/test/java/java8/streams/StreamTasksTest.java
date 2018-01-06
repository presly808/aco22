package java8.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StreamTasksTest {

    private List<User> actualUsers;
    private List<User> expectedUsers;
    private StreamTasks tasks;

    @Before
    public void setUp() throws Exception {

        List<User> users = new ArrayList<>();
        actualUsers = new ArrayList<>();
        expectedUsers = new ArrayList<>();
        tasks = new StreamTasks(new ArrayList<>());

        users.add(new User(1, "Vasya", 10, "man",
                100, "Kiev", "department1"));
        users.add(new User(2, "Petya", 20, "man",
                200, "Kiev", "department2"));
        users.add(new User(3, "Igor", 30, "man",
                300, "Kiev", "department3"));
        users.add(new User(4, "Roman", 40, "man",
                400, "Kiev", "department4"));
        users.add(new User(5, "Ivan", 15, "man",
                500, "Odessa", "department1"));
        users.add(new User(6, "Anton", 25, "man",
                600, "Odessa", "department2"));
        users.add(new User(7, "Antonina", 35, "woman",
                700, "Odessa", "department3"));
        users.add(new User(8, "Sveta", 27, "woman",
                800, "Kiev", "department1"));
        users.add(new User(9, "Katya", 23, "woman",
                900, "Kiev", "department2"));
        users.add(new User(10, "Elena", 16, "woman",
                1000, "Kiev", "department3"));

        tasks = new StreamTasks(users);
    }

    @Test
    public void findUsersBySexAgeCity() throws Exception {

        actualUsers = tasks.findUsersBySexAgeCity(25, 30, "woman", "Kiev");
        expectedUsers.add(new User(8, "Sveta", 27, "woman",
                800, "Kiev", "department1"));

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void findFirstNUsersSortedBySallery() throws Exception {

        actualUsers = tasks.findFirstNUsersSortedBySallery(3);
        expectedUsers.add(new User(8, "Sveta", 27, "woman",
                800, "Kiev", "department1"));
        expectedUsers.add(new User(9, "Katya", 23, "woman",
                900, "Kiev", "department2"));
        expectedUsers.add(new User(10, "Elena", 16, "woman",
                1000, "Kiev", "department3"));

        assertEquals(expectedUsers, actualUsers);

    }

    @Test
    public void sumOfUsersSalary() throws Exception {

        double actual = tasks.sumOfUsersSalary("Odessa", "A");
        assertEquals(1300, actual, 0.001);

    }

    @Test
    public void addSalaryPercent() throws Exception {
        tasks.addSalaryPercent(5);
        assertEquals(105, tasks.getUsers().get(0).getSalary(), 0.001);
    }

    @Test
    public void countUsersLiveInCity() throws Exception {
        long count = tasks.countUsersLiveInCity("Kiev");
        assertEquals(7, count);

    }

    @Test
    public void avarageSalaryUsersLiveInCity() throws Exception {
        double salary = tasks.avarageSalaryUsersLiveInCity("Kiev");
        assertEquals(528.571, salary, 0.001);
    }

    @Test
    public void sumSallaryGroupedByDepartment() throws Exception {

        double sumSalary = tasks.sumSallaryGroupedByDepartment("department1");
        assertEquals(1400, sumSalary, 0.001);


    }

}