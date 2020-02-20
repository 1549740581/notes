package nwpu.sherman;

/**
 * 局部变量表和操作数栈
 * https://www.bilibili.com/video/av35040927/?spm_id_from=333.788.b_636f6d6d656e74.50
 *
 * @author sherman
 */
public class Question01 {
    public static void main(String[] args) {
        // output: 4, 1, 11
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println(i + ", " + j + ", " + k);
    }
}
