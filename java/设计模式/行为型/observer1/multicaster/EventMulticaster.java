package fun.sherman.observer1.multicaster;

import fun.sherman.observer1.event.WeatherEvent;
import fun.sherman.observer1.listener.WeatherListener;

/**
 * 抽象事件多播器
 * Created on 2020/5/11
 *
 * @author sherman tang
 */
public interface EventMulticaster {
    /**
     * 多播器广播事件，寻找合适的监听器进行处理
     * @param weatherEvent 待广播事件
     */
    void multicastEvent(WeatherEvent weatherEvent);


    /**
     * 添加新的监听器
     * @param weatherListener 待添加的监听器
     */
    void addListener(WeatherListener weatherListener);

    /**
     * 删除给定的监听器
     * @param weatherListener 待删除的监听器
     */
    void deleteListener(WeatherListener weatherListener);

}
