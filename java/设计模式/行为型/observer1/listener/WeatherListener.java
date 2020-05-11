package fun.sherman.observer1.listener;

import fun.sherman.observer1.event.WeatherEvent;

/**
 * Created on 2020/5/11
 *
 * @author sherman tang
 */
public interface WeatherListener {
    /**
     * 监听Weather事件
     * @param event 监听的事件
     */
    void onWeatherEvent(WeatherEvent event);
}
