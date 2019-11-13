package org.hpms.gui.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

    public static void copyFile(String source, String dest) throws Exception {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
}
