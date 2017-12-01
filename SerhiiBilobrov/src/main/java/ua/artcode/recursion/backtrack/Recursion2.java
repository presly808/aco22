package ua.artcode.recursion.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by serhii on 26.11.17.
 */
public class Recursion2 {

    public List<Integer> doubling(List<Integer> nums) {

        Function<Integer, Integer> function1 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 2;
            }
        };

        Function<Integer, Integer> function2 =
                (Integer integer) -> {
                    return integer * 2;
                };

        Function<Integer, Integer> function3 =
                (integer) -> {
                    return integer * 2;
                };

        Function<Integer, Integer> function4 =
                (integer) -> integer * 2;
        ;

        return nums.stream().map(e -> e * 2).collect(Collectors.toList());
    }


    public static boolean hasSeq(int[] arr, int target) {

        if (target == 0) {
            return true;
        }

        if (arr.length == 0) {
            return false;
        }


        int way1 = target - arr[0];
        int way2 = target;

        int[] tail = Arrays.copyOfRange(arr, 1, arr.length);

        boolean res1 = hasSeq(tail, way1);
        boolean res2 = hasSeq(tail, way2);

        return res1 || res2;
    }

    public static List<Integer> retSeq(int[] arr, List<Integer> list, int target) {

        if (target == 0) {
            return list;
        }

        if (arr.length == 0) {
            return null;
        }


        int way1 = target - arr[0];
        List<Integer> copy1 = cloneList(list);
        copy1.add(arr[0]);

        int way2 = target;
        List<Integer> copy2 = cloneList(list);


        int[] tail = Arrays.copyOfRange(arr, 1, arr.length);

        List<Integer> res2 = retSeq(tail, copy2, way2);

        List<Integer> res1 = retSeq(tail, copy1, way1);

        return res1 != null ? res1 : res2;
    }

    private static List<Integer> cloneList(List<Integer> list) {
        List<Integer> copy = new ArrayList<>();

        copy.addAll(list);

        return copy;
    }
}
