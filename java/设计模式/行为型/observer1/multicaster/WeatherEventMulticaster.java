package fun.sherman.observer1.multicaster;

/**
 * Created on 2020/5/11
 *
 * @author sherman tang
 */
public class WeatherEventMulticaster extends AbstractEventMulticaster {

    @Override
    public void doStart() {
        System.out.println("处理事件之前的钩子方法");
    }

    @Override
    public void doEnd() {
        System.out.println("处理事件之后的钩子方法");
    }
}
