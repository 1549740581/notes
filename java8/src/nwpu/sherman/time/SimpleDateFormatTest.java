package nwpu.sherman.time;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Java8之前的时间类API都是线程不安全的
 *
 * @author sherman
 */
public class SimpleDateFormatTest {

    /**
     * java.util.concurrent.ExecutionException:
     * java.lang.NumberFormatException: multiple points
     */
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Callable<Date> task = () -> sdf.parse("2019-11-25");

        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<Date>> results = new ArrayList<>();
        int threadNums = 10;
        for (int i = 0; i < threadNums; i++) {
            results.add(pool.submit(task));
        }
        for (Future<Date> future : results) {
            System.out.println(future.get());
        }
        pool.shutdown();
    }


    /**
     * 使用ThreadLocal类来解决SimpleDateFormat问题
     */
    private static final ThreadLocal<DateFormat> df = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd")
    );

    private static Date convert(String source) throws ParseException {
        return df.get().parse(source);
    }

    @Test
    public void threadLocalTest() throws Exception {
        int threadNums = 10;
        Callable<Date> task = () -> convert("2019-11-25");
        ExecutorService pool = Executors.newFixedThreadPool(threadNums);
        List<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < threadNums; i++) {
            results.add(pool.submit(task));
        }
        for (Future<Date> result : results) {
            System.out.println(result.get());
        }
        pool.shutdown();
    }

    /**
     * Java8 时间类新API代替原来的SimpleDateFormat & ThreadLocal形式
     *
     * @throws Exception
     */
    @Test
    public void localDateTest() throws Exception {
        int threadNums = 10;
        List<Future<LocalDate>> results = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Callable<LocalDate> task = () -> LocalDate.parse("2019-11-25", dtf);
        ExecutorService pool = Executors.newFixedThreadPool(threadNums);
        for (int i = 0; i < threadNums; i++) {
            results.add(pool.submit(task));
        }
        for (Future<LocalDate> result : results) {
            System.out.println(result.get());
        }
    }
}
