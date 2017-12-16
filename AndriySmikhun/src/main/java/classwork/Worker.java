package classwork;

import java.util.*;
import java.util.stream.Collectors;

public class Worker {
    private List<User> users = new ArrayList<>();

    public Worker(List<User> users) {
        this.users = users;
    }

    public Worker() {
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean filterByCityAndAge() {
        users.stream().filter(s -> s.getSity().equals("Kyiv") && (s.getAge() >= 25 || s.getAge() >= 30))
                .forEach(s -> System.out.println(s.getName()));
        return true;
    }

    public boolean topThree() {
        users.stream().sorted(new CompareUserBySalery()).limit(3)
                .forEach(s -> System.out.println(s.getName()));
        return true;
    }

    public boolean filterByOdessa() {
        users.stream().filter(s -> s.getSity().equals("Odessa")).filter(s -> s.getName().contains("A"))
                .forEach(s -> System.out.println(s.getName()));
        return true;

    }

    public boolean plus5Percent() {
        List<User> tmp = users.stream()
                .peek((p) -> p.setSalary(p.getSalary() + (int) (p.getSalary() * 0.02)))
                .collect(Collectors.toList());

        tmp.stream()
                .forEach(s -> System.out.println(
                        "id = " + s.getId() +
                                " Name = " + s.getName() +
                                " Salery = " + s.getSalary()));
        return true;
    }

    public boolean liveInKiev() {
        long i = users.stream()
                .filter(s -> s.getSity()
                        .equals("Kyiv")).count();
        System.out.println("Count users live in Kyiv " + i);
        return true;
    }

    public boolean avarageSalary() {
        OptionalDouble avagage = users.stream()
                .filter(s -> s.getSity().equals("Kyiv") && s.getSex().equals("men"))
                .mapToInt(s -> s.getSalary()).average();
        System.out.println(avagage);
        return true;
    }

    public boolean byGroup() {
        Map<String, List<User>> tmp = users.stream().collect(Collectors.groupingBy(User::getDepartment));

        for (Map.Entry<String, List<User>> item : tmp.entrySet()) {
            System.out.println(item.getKey());
            item.getValue().stream().sorted(new CompareUserBySalery())
                    .forEach(s -> System.out.println(s.getName() + " Salery " + s.getSalary()));


            System.out.println(" ");
        }
        return true;
    }

}

class CompareUserBySalery implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        int salery = o2.getSalary() - o1.getSalary();
        if (salery == 0) {
            return 0;
        }
        return salery;
    }
}
