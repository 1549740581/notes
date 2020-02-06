package nwpu.sherman.optional;

/**
 * 使用Optional类封装两个类：
 *      * Man类
 *      * Goddess类
 *          Man类中有Goddess成员变量，Goddess中有String(name)成员变量
 *          Man中的Goddess可能为null，但是Goddess的name一定不为null
 *      比较使用Optional前后代码的形式
 *
 * @author sherman
 */

import org.junit.Test;

import java.util.Optional;

/**
 * 每个Goddess一定有name属性
 */
class Goddess {
    private String name;

    public String getName() {
        return name;
    }

    public Goddess(String name) {
        this.name = name;
    }
}

/**
 * 不是所有的Man都一定有goddess
 * 因此访问Man的goddess属性时要进行判断
 */
class Man {
    private Goddess goddess;

    public Goddess getGoddess() {
        return goddess;
    }

    public Man(Goddess goddess) {
        this.goddess = goddess;
    }
}

/**
 * 男生的女神使用Optional进行封装
 */
class NewMan {
    private Optional<Goddess> goddess = Optional.empty();

    public Optional<Goddess> getGoddess() {
        return goddess;
    }

    public NewMan(Optional<Goddess> goddess) {
        this.goddess = goddess;
    }

    public NewMan() {
    }
}

/**
 * 演示使用Optional类来包装可能为null的对象前后代码编写情况
 *
 * @author sherman
 */
public class OptionalAppTest {

    @Test
    public void optionalApp01Test() {
        // 有女神返回对应的女神姓名
        Man sherman = new Man(new Goddess("WangQiyao"));
        System.out.println(getGoddessNameWithoutOptional(sherman));

        // 没有女神，默认返回wqy
        Man diaosi = new Man(null);
        System.out.println(getGoddessNameWithoutOptional(diaosi));
    }

    @Test
    public void optionalApp02Test() {
        Optional<Goddess> wqy = Optional.of(new Goddess("WangQiyao"));
        Optional<NewMan> sherman = Optional.of(new NewMan(wqy));
        System.out.println(getGoddessNameWithOptional(sherman));
    }

    /**
     * 获取男生心中女神的名字，不使用Optional进行包装
     */
    private String getGoddessNameWithoutOptional(Man man) {
        if (man == null) {
            // 如果男生为空，默认返回"wqy"
            return "wqy";
        } else {
            Goddess goddess = man.getGoddess();
            if (goddess == null) {
                // 如果男生没有女神，默认返回"wqy"
                return "wqy";
            } else {
                return goddess.getName();
            }
        }
    }

    /**
     * 获取男生心中女神的名字，使用Optional进行包装
     */
    private String getGoddessNameWithOptional(Optional<NewMan> man) {
        return man.orElse(new NewMan())
                .getGoddess()
                // 没有女神，默认为"wqy"
                .orElse(new Goddess("wqy"))
                .getName();
    }
}
