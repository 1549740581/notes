package fun.sherman.observer1.listener;

import fun.sherman.observer1.event.RainWeatherEvent;
import fun.sherman.observer1.event.WeatherEvent;

/**
 * Created on 2020/5/11
 *
 * @author sherman tang
 */
public class RainWeatherListener implements WeatherListener {
    @Override
    public void onWeatherEvent(WeatherEvent event) {
        if (event instanceof RainWeatherEvent) {
            RainWeatherEvent rainWeatherEvent = (RainWeatherEvent) event;
            rainWeatherEvent.getWeather();
            System.out.println("监听到[rain event]，开始处理...");
        }
    }
}
