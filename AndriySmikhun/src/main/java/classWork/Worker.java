package classWork;

import java.util.*;
import java.util.stream.Collectors;

public class Worker {
    private List<User> users = new ArrayList<>();

    public Worker(List<User> users) {
        this.users = users;
    }

    public Worker() {

    }

    public void showByName(List<Worker> users) {
        users.stream().forEach(s -> System.out.println(s));
    }

    public void filterByCityAndAge() {
        users.stream().filter(s -> s.getSity().equals("Kyiv") && (s.getAge() >= 25 || s.getAge() >= 30))
                .forEach(s -> System.out.println(s.getName()));
    }

    public void topThree() {
        users.stream().sorted(new CompareUserBySalery()).limit(3)
                .forEach(s -> System.out.println(s.getName()));
    }

    public void filterByOdessa() {
        users.stream().filter(s -> s.getSity().equals("Odessa")).filter(s -> s.getName()
                .contains("A")).forEach(s -> System.out.println(s.getName()));

    }

    public void plus5Percent() {
        List<User> tmp = users.stream()
                .peek((p) -> p.setSalary(p.getSalary() + (int) (p.getSalary() * 0.02)))
                .collect(Collectors.toList());

        tmp.stream()
                .forEach(s -> System.out.println(
                        "id = " + s.getId() +
                                " Name = " + s.getName() +
                                " Salery = " + s.getSalary()));
    }

    public void liveInKiev(){
        long i = users.stream().filter( s -> s.getSity().equals("Kyiv")).count();
        System.out.println("Count users live in Kyiv " + i);
    }

    public void avarageSalary(){
        OptionalDouble avagage = users.stream()
                .filter(s -> s.getSity().equals("Kyiv") && s.getSex().equals("men"))
                .mapToInt(s -> s.getSalary()).average();
        System.out.println(avagage);
    }

    public void byGroup(){
        Map <String ,List<User>> tmp = users.stream().collect(Collectors.groupingBy(User ::getDepartment ));
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

class CompareUserByName implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getName().compareTo(o2.getName());
    }
}