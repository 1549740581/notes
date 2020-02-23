package nwpu.sherman.time;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * 1、TemporalAdjuster：时间矫正器
 *
 * 2、DateTimeFormatter：日期格式化
 *
 * 3、ZoneDate、ZonedTime、ZonedDateTime：带时区的时间操作
 *
 * @author sherman
 */
public class OtherTest {
    /**
     * 时间矫正器
     */
    @Test
    public void temporalAjusterTest() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime nextSunday = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(nextSunday);
    }

    /**
     * 日期格式化
     */
    @Test
    public void dateTimeFormatterTest() {
        // 使用默认格式化方法
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);
        System.out.println(strDate);
        System.out.println("====================================");

        // 自定义格式化方法
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH/mm/ss");
        String strDate2 = dtf2.format(ldt);
        System.out.println(strDate2);

        // 解析
        LocalDateTime newDate = LocalDateTime.parse(strDate2, dtf2);
        System.out.println(newDate);
    }

    /**
     * Zone时区时间
     */
    @Test
    public void zoneTimeTest() {
        // 显示所有可用的时区
        ZoneId.getAvailableZoneIds().forEach(System.out::println);

        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);

        // 组装成带时区的格式
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zdt);
    }
}
