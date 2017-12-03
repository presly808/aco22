package ua.artcode.market.models;

import java.util.ArrayList;
import java.util.List;

public class Salesman {

    private String fullName;
    private String login;
    private int pass;
    private int id;

    private double sumOfAllSales;
    private double salary;

    private boolean director;
    private List<Salesman> subordinates = new ArrayList<>();

    public Salesman(String name) {
        this.fullName = name;
    }

    public Salesman(String fullName, String login, int pass, int id) {
        this.fullName = fullName;
        this.login = login;
        this.pass = pass;
        this.id = id;
    }

    public Salesman(String fullName, String login, int pass, int id, boolean isDirector) {
        this.fullName = fullName;
        this.login = login;
        this.pass = pass;
        this.id = id;
        this.director = isDirector;
    }

    public void addSum(double sum) {
        this.sumOfAllSales += sum;
    }

    @Override
    public String toString() {
        return String.format("name: %s, sum of all sales: %.2f", fullName, sumOfAllSales);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Salesman other = (Salesman) obj;

        return (fullName != null && fullName.equals(other.fullName)) &&
                (login != null && login.equals(other.login)) &&
                pass == other.pass &&
                sumOfAllSales == other.sumOfAllSales &&
                salary == other.salary &&
                subordinates.size() == other.subordinates.size() &&
                subordinates.containsAll(other.subordinates);
    }

    public int getPass() {
        return pass;
    }

    public String getLogin() {
        return login;
    }

    public String getFullName() {
        return fullName;
    }

    public double getSumOfAllSales() {
        return sumOfAllSales;
    }

    public void setSumOfAllSales(double sumOfAllSales) {
        this.sumOfAllSales = sumOfAllSales;
    }

    public int getId() {
        return id;
    }

    public List<Salesman> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Salesman> subordinates) {
        this.subordinates = subordinates;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isDirector() {
        return director;
    }

    public void setDirector(boolean director) {
        this.director = director;
    }
}
/*
package artcode.data_structure.dynamic;

        import java.util.Arrays;
        import java.util.List;

public class Node {

    public Object val;
    public Node next;

    public Node() {
    }

    public Node(Object val, Node next) {

        this.val = val;
        this.next = next;
    }
}
package artcode.recursion.tree_task;

        import java.util.Arrays;
        import java.util.List;

public class TreeNodeUtils {

    public static int count(artcode.recursion.tree_task.TreeNode root) {

        int amount = root.amount;

        List<artcode.recursion.tree_task.TreeNode> children = root.children;

        if (children == null) {
            return amount;
        }

        for (artcode.recursion.tree_task.TreeNode child : children) {
            int returnRes = count(child);
            amount += returnRes;

        }

        return amount;


    }
}
package artcode.recursion.tree_task;

        import java.util.List;

public class TreeNode {

    public int amount;
    public List<artcode.recursion.tree_task.TreeNode> children;

    public TreeNode() {
    }

    public TreeNode(int amount) {
        this.amount = amount;
    }

    public TreeNode(int amount, List<artcode.recursion.tree_task.TreeNode> slaves) {
        this.amount = amount;
        this.children = slaves;
    }
}
package artcode.recursion.tree_task;

        import java.util.Arrays;

public class TestCrrationTree {

    public static void main(String[] args) {

        artcode.recursion.tree_task.TreeNode employee1 = new artcode.recursion.tree_task.TreeNode(1000);
        artcode.recursion.tree_task.TreeNode employee2 = new artcode.recursion.tree_task.TreeNode(1000);
        artcode.recursion.tree_task.TreeNode employee3 = new artcode.recursion.tree_task.TreeNode(1000);
        artcode.recursion.tree_task.TreeNode employee4 = new artcode.recursion.tree_task.TreeNode(1000);

        artcode.recursion.tree_task.TreeNode manager1 = new artcode.recursion.tree_task.TreeNode(200, Arrays.asList(employee1, employee2));
        artcode.recursion.tree_task.TreeNode manager2 = new artcode.recursion.tree_task.TreeNode(200, Arrays.asList(employee3, employee4));
        artcode.recursion.tree_task.TreeNode manager3 = new artcode.recursion.tree_task.TreeNode(200, Arrays.asList(new artcode.recursion.tree_task.TreeNode(1000)));


        artcode.recursion.tree_task.TreeNode director = new artcode.recursion.tree_task.TreeNode(1000,
                Arrays.asList(manager1, manager2, manager3));

        int amount = artcode.recursion.tree_task.TreeNodeUtils.count(director);
    }
}
package artcode.recursion;

public class IntroRec {

    public static void main(String[] args) {
        a1(10);
    }


    // infinite recursion, StackOverFlow
    public static void a1(int num){
        if(num == 0){
            return;
        }
        System.out.println(num);
        a1(num);
    }

    public static void a2(int num){
        if(num == 0){
            return;
        }
        System.out.println(num);
        a2(num - 1);
    }

    public static void a3(int num){
        a3(num - 1);
        if(num == 0){
            return;
        }
        System.out.println(num);
    }

    // 10 ->
    public static int a4(int num){
        if(num == 0){
            return 1;
        }

        int returnedRes = a4(num - 2);

        return returnedRes + 1;
    }
}
*/
