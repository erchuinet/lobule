package com.erchuinet.common;

import java.util.HashSet;

public class ArrayUtils {

    private ArrayUtils() {}

    public static String[] distinct(String[] arr) {
        if(arr==null || arr.length==0) {
            return arr;
        }
        HashSet<String> set = new HashSet<>();
        for (String str : arr) {
            set.add(str);
        }
        return set.toArray(new String[0]);
    }
}
