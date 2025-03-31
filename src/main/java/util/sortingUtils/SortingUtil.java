package util.sortingUtils;

import entity.Booking;

import java.util.*;

public class SortingUtil {

    public static <V> Map<Integer, V> sortById(HashMap<Integer, V> hashMap) {
        return new TreeMap<>(hashMap);
    }

}
