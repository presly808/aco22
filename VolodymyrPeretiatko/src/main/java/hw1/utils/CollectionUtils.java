package hw1.utils;

import java.util.List;

public class CollectionUtils {

    public static boolean listContainElementsOther(List src, List list){
        for (Object o : list){
            if (src.contains(o)){
                return true;
            }
        }
        return false;
    }

}