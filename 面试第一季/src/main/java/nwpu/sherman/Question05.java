package nwpu.sherman;

/**
 * 成员变量和局部变量
 *
 * @author sherman
 */
public class Question05 {
    static int s;
    int i;
    int j;

    {
        /**
         * 局部变量i，i++操作也是操作在局部变量i上
         */
        int i = 1;
        i++;
        /**
         * 实例变量
         */
        j++;
        /**
         * 类变量
         */
        s++;
    }

    public void test(int j) {
        /**
         * j也是局部变量，j++操作也是操作在局部变量上
         */
        j++;
        /**
         * 实例变量
         */
        i++;
        /**
         * 类变量
         */
        s++;
    }

    public static void main(String[] args) {
        Question05 obj1 = new Question05();
        Question05 obj2 = new Question05();
        obj1.test(10);
        obj1.test(20);
        obj2.test(10);
        System.out.println(obj1.i + ", " + obj1.j + ", " + obj1.s);     // 2, 1, 5
        System.out.println(obj2.i + ", " + obj2.j + ", " + obj2.s);     // 1, 1, 5
    }
}
