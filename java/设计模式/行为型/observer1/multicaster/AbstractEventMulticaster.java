package fun.sherman.observer1.multicaster;

import fun.sherman.observer1.event.WeatherEvent;
import fun.sherman.observer1.listener.WeatherListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/5/11
 *
 * @author sherman tang
 */
public abstract class AbstractEventMulticaster implements EventMulticaster {
    private List<WeatherListener> listeners = new ArrayList<>();

    @Override
    public void multicastEvent(WeatherEvent weatherEvent) {
        doStart();
        listeners.forEach(e -> e.onWeatherEvent(weatherEvent));
        doEnd();
    }

    @Override
    public void addListener(WeatherListener weatherListener) {
        listeners.add(weatherListener);
    }

    @Override
    public void deleteListener(WeatherListener weatherListener) {
        listeners.remove(weatherListener);
    }

    /**
     * 处理事件之前的钩子方法
     */
    public abstract void doStart();

    /**
     * 处理事件之后的钩子方法
     */
    public abstract void doEnd();
}
