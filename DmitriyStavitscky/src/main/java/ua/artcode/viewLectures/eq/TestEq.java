package ua.artcode.viewLectures.eq;

import java.util.Arrays;

public class TestEq {

    public static void main(String[] args) {
        String s1 = new String("1234");
        String s2 = new String("1234");

        System.out.println(s1.equals(s2));
        System.out.println(s2.equals(s1));

        System.out.println();

        Dog dog1 = new Dog("Barsik", 7, new int[]{1,2,3,4,5});
        Dog dog2 = new Dog("Barsik", 7, new int[]{1,2,3,4,5});

        System.out.println(dog1 == dog2);
        System.out.println(dog1.equals(dog2));

        // reflection
        // dog1.equals(dog1) -> true
        // symmetric
        // dog1.equals(dog2) == dog2.equals(dog1)
        // transitive
        // x == y && y == z -> x == z
        // x == null -> false

    }
}


class Dog {

    private int id; // unique
    private String name;
    private int age;
    private int[] bones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int[] getBones() {
        return bones;
    }

    public void setBones(int[] bones) {
        this.bones = bones;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", bones=" + Arrays.toString(bones) +
                '}';
    }

    public Dog(String name, int age, int[] bones) {
        this.name = name;
        this.age = age;
        this.bones = bones;
    }

    // x == x -> true
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || obj.getClass() != Dog.class){
            return false;
        }

        Dog other = (Dog) obj;

        return age == other.age &&
                Arrays.equals(bones, other.bones) &&
                name != null &&
                name.equals((other.name));

    }
}


