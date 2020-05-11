package fun.sherman.observer1;

import fun.sherman.observer1.event.RainWeatherEvent;
import fun.sherman.observer1.event.SnowWeatherEvent;
import fun.sherman.observer1.listener.RainWeatherListener;
import fun.sherman.observer1.listener.SnowWeatherListener;
import fun.sherman.observer1.multicaster.EventMulticaster;
import fun.sherman.observer1.multicaster.WeatherEventMulticaster;

/**
 * Created on 2020/5/11
 *
 * @author sherman tang
 */
public class MainTest {
    public static void main(String[] args) {
        // 创建事件多播器
        EventMulticaster multicaster = new WeatherEventMulticaster();
        // 为多播器添加监听器
        multicaster.addListener(new SnowWeatherListener());
        multicaster.addListener(new RainWeatherListener());
        // 多播器处理相应事件
        multicaster.multicastEvent(new RainWeatherEvent());
        System.out.println("----------------------");
        multicaster.multicastEvent(new SnowWeatherEvent());
    }
}
