package nwpu.sherman;

import java.util.Arrays;

/**
 * 方法参数传递机制
 *
 * @author sherman
 */
class MyData {
    int a = 10;
}

public class Question04 {
    public static void main(String[] args) {
        int i = 0;
        String string = "hello";
        Integer integer = 100;
        int[] arr = {1, 2, 3, 4};
        MyData myData = new MyData();
        change(i, string, integer, arr, myData);
        System.out.println(i);                          // 0
        System.out.println(string);                     // hello
        System.out.println(integer);                    // 100
        System.out.println(Arrays.toString(arr));       // [2, 2, 3, 4]
        System.out.println(myData.a);                   // 11
    }

    /**
     * @param i       基本类型，直接copy一份新的局部变量
     * @param str     指向常量池中新的常量
     * @param integer 指向缓存中新的数字
     * @param arr     原地操作
     * @param m       原地操作
     */
    public static void change(int i, String str, Integer integer, int[] arr, MyData m) {
        i += 1;
        str += "world";
        integer += 1;
        arr[0] += 1;
        m.a += 1;
    }
}
