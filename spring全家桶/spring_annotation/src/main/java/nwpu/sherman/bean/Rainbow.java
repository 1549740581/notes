package nwpu.sherman.bean;

/**
 * 演示@Autowire在构造器、方法和参数上的自动装配
 *
 * @author sherman
 */
public class Rainbow {
    private Red red;

    // @Autowired
    public Rainbow(Red red) {
        this.red = red;
    }

    @Override
    public String toString() {
        return "Rainbow{" +
                "red=" + red +
                '}';
    }

    public Red getRed() {
        return red;
    }

    public void setRed(Red red) {
        this.red = red;
    }
}
