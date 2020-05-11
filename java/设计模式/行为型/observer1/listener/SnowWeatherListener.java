package fun.sherman.observer1.listener;

import fun.sherman.observer1.event.SnowWeatherEvent;
import fun.sherman.observer1.event.WeatherEvent;

/**
 * Created on 2020/5/11
 *
 * @author sherman tang
 */
public class SnowWeatherListener implements WeatherListener {
    @Override
    public void onWeatherEvent(WeatherEvent event) {
        if (event instanceof SnowWeatherEvent) {
            SnowWeatherEvent snowWeatherEvent = (SnowWeatherEvent) event;
            snowWeatherEvent.getWeather();
            System.out.println("监听到[snow event]，开始处理...");
        }
    }
}
