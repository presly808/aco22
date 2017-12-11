package ua.artcode.exclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by serhii on 03.12.17.
 */
public class Java8Test {
    public static void main(String[] args) {

        Java8Test.pr("hello");

        List<List<Integer>> integers =
                Arrays.asList(
                        Arrays.asList(1,2,3),
                        Arrays.asList(1,2,3),
                        Arrays.asList(1,2,3),
                        Arrays.asList(1,2,3),
                        Arrays.asList(1,2,3));

        List<List<Integer>> list = new ArrayList<>(integers);

        integers.stream().flatMap(list1 -> list1.stream()).collect(Collectors.toList());
    }

    public static void pr(Object o){
        System.out.println(o);
    }
}
