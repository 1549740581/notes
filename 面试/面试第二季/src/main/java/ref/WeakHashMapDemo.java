package ref;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author sherman
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        Map<Integer, Object> weakHashMap = new WeakHashMap<>();
        Map<Integer, Object> hashMap = new HashMap<>();

        /**
         * 注意这里两个map不能共用一对key-value，会相互影响
         */
        Integer key1 = new Integer(250);
        String value1 = "value1";
        Integer key2 = new Integer(250);
        String value2 = "value2";

        weakHashMap.put(key1, value1);
        hashMap.put(key2, value2);
        System.out.println("weakHashMap: " + weakHashMap);
        System.out.println("hashMap: " + hashMap);
        key1 = null;
        key2 = null;
        System.gc();
        System.out.println("==========================");
        System.out.println("weakHashMap: " + weakHashMap);
        System.out.println("hashMap: " + hashMap);
    }
}