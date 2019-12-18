package cme;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author sherman
 */
public class ArrayListThreadUnsafe {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            new Thread(() -> {
                lists.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(lists);
            }).start();
        }
    }
}
