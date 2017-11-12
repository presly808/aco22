package ua.artcode.viewLectures;

import java.util.Arrays;
import java.util.Comparator;

public class TestArrays {

    public static void main(String[] args) {
        int[] mas = {2,3,4,5,6,7,8,};

        int[] cloned =
                Arrays.copyOf(mas, mas.length);

        int[] rangeMas =
                Arrays.copyOfRange(mas, 1,3);

        Arrays.equals(mas, cloned);

        String strMas = Arrays.toString(mas);

        Arrays.fill(rangeMas, 2); // fill array 2

        Arrays.sort(mas);

        Cat[] cats = new Cat[3];

        cats[0] = new Cat("Vaska", 5, 3);
        cats[1] = new Cat("Rizik", 3, 7);
        cats[2] = new Cat("Bolka", 2, 6);

        Object[] m = cats;

        Arrays.sort(cats);
        String res = Arrays.toString(cats);
        res = res.replaceAll("\\}", "\\}\n"); // } -> }\n

        System.out.println(res);

        Arrays.sort(cats, new CatNameComparator());

        // Arrays.sort(cats, (a,b) -> b.getAge() - a.getAge());

        System.out.println(Arrays.toString(cats ).replaceAll("\\}", "\\}\n"));

        // Arrays.stream(cats).forEach((cat -> System.out.println(cat)));


    }
}

class Cat implements Comparable<Cat> {

    private String petName;
    private double weight;
    private int age;

    public Cat(String petName, double weight, int age) {
        this.petName = petName;
        this.weight = weight;
        this.age = age;
    }

    public String getPetName() {
        return petName;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "petName='" + petName + '\'' +
                ", weight=" + weight +
                ", age=" +age +
                '}';


    }

   /* @Override
    public int compareTo(Object o) {
        Cat other = (Cat) o;

        *//*return this.age > other.age ?
                1 : this.age < other.age ? -1 : 0;*//*

        return this.age - other.age;
    }
*/
    @Override
    public int compareTo(Cat o) {
        return this.age - o.age;
    }
}

class CatNameComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.getPetName().compareTo(o2.getPetName());
    }
}