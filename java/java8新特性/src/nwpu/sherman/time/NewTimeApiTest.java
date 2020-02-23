package nwpu.sherman.time;

import org.junit.Test;

import java.time.*;

/**
 * 1、LocalDate & LocalTime & LocalDateTime
 *   这三个类是不可变对象，分别表示使用ISO-8601日历系统的日期、时间、日期和时间
 *      * now():            根据当前时间创建对象
 *      * of():             根据指定时间/日期创建对象
 *      * 增加时间：
 *          - plusDays
 *          - plusWeeks
 *          - plusMonths
 *          - plusYears
 *      * 减少时间:
 *          - MinusDays
 *          - MinusWeeks
 *          - MinusMonths
 *          - MinusYears
 *      * 获取对应时间对象/值
 *          - getMonth
 *          - getMonthValue
 *          - getYear
 *      * 修改月份、年份天数：
 *          - withDayOfMonth
 *          - withDayOfYear
 *          - withMonth
 *          - withYear
 *      * 获取月份、年份的第几天：和上一点相反
 *          - getDayOfMonth
 *          - getDayOfYear
 *          - getDayOfWeek
 *      * 比较两个LocalDate时间先后：
 *          - isBefore
 *          - isAfter
 *
 * 2、Instant：时间戳（以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒数）
 *      * now:                      默认获取UTC时区
 *      * atOffSet:                 修改时区
 *      * toEpochMilli:             获取过去的秒数
 *      * Instant.ofEpochSecond:    在当前时间戳基础上增加分钟数
 *
 * 3、Duration：获取两个“时间”之间间隔
 *      * Duration.between
 *
 * 4、Period：获取两个“日期”之间间隔
 *      * Period.between
 *
 * @author sherman
 */
public class NewTimeApiTest {

    /**
     * LocalDateTime 测试
     */
    @Test
    public void localDateTimeTest(){
        // now()
        // ldt01 -> 2019-11-25T15:19:59.927
        LocalDateTime ldt01 = LocalDateTime.now();
        System.out.println(ldt01);

        // of()
        LocalDateTime ldt02 = LocalDateTime.of(2019, 11, 25, 15, 9, 00);
        System.out.println(ldt02);

        // plus
        System.out.println(ldt01.plusYears(2).plusMonths(1).plusHours(2));

        // minus
        System.out.println(ldt01.minusMonths(3).minusWeeks(1).minusMinutes(10));

        // get()
        System.out.println(ldt01.getYear());
        System.out.println(ldt01.getMonth());
        System.out.println(ldt01.getMonthValue());

        // withDayOfXxx

        // ldt01的第365天，即2019-12-31
        LocalDateTime modify = ldt01.withDayOfYear(365);
        System.out.println(modify);

        // ldt01当前月的第10天，即2019-11-10
        LocalDateTime modifyMonth = ldt01.withDayOfMonth(10);
        System.out.println(modifyMonth);

        // isBefore、isAfter
        System.out.println(ldt01.isAfter(ldt02));
    }

    /**
     * Instant 测试
     */
    @Test
    public void instantTest() {
        // now
        Instant now = Instant.now();
        System.out.println(now);

        // atOffSet 转换成当前东八区时间
        OffsetDateTime offset = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offset);

        // toEpochSecond
        long timeStamp = offset.toEpochSecond();
        System.out.println(timeStamp);

        // ofEpochSecond 相对于now增加了1分钟时间
        Instant ofEpochSecond = Instant.ofEpochSecond(timeStamp + 60);
        System.out.println(ofEpochSecond);
    }
    /**
     * Duration 测试
     */
    @Test
    public void durationTest(){
        Instant from = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant to = Instant.now();
        System.out.println(Duration.between(from, to).toMillis());
    }

    /**
     * Period 测试
     */
    @Test
    public void periodTest() {
        LocalDate ld1 = LocalDate.of(1996, 3, 21);
        LocalDate now = LocalDate.now();
        System.out.println(Period.between(ld1, now).getYears());
    }
}
