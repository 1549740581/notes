package fun.sherman.observer1.event;

/**
 * Created on 2020/5/11
 *
 * @author sherman tang
 */
public class RainWeatherEvent extends WeatherEvent {

    @Override
    public void getWeather() {
        System.out.println("[rain event] 事件发生");
    }
}
