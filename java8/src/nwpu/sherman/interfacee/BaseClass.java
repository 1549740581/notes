package nwpu.sherman.interfacee;

/**
 * BaseClass中有和NewInterface中重名的方法getName()，演示“类优先”原则
 *
 * @author sherman
 */
public class BaseClass {
    public String getName() {
        return "BaseClass";
    }
}
