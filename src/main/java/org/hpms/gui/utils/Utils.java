package org.hpms.gui.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static <T> List<T> singletonList(T element) {
        List<T> list = new ArrayList<>(1);
        list.add(element);
        return list;
    }

    public static <K, V> Map<K, V> singletonMap(K key, V element) {
        Map<K, V> map = new HashMap<>(1);
        map.put(key, element);
        return map;
    }
}
