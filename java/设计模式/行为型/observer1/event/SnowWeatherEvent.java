package fun.sherman.observer1.event;

/**
 * Created on 2020/5/11
 *
 * @author sherman tang
 */
public class SnowWeatherEvent extends WeatherEvent {
    @Override
    public void getWeather() {
        System.out.println("[snow event] 发生");
    }
}
