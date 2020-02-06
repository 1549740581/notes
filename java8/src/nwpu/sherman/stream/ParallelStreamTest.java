package nwpu.sherman.stream;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Java8 中Fork/Join框架和传统线程池相比：
 *      * 采用“工作窃取”模式（work-stealing）：
 *          当执行新的任务时它可以将其拆分成更小的任务执行，并将小任务添加到线程队列中，然后随机在从
 *          一个线程队列中偷取一个任务并将它放在自己的队列中
 *      * 相比传统的线程池，Fork/Join框架的优势体现在对其中包含的任务的处理方式上：
 *          在一般线程池中，如果一个线程正在执行的任务由于某些原因无法继续执行，那么该线程会处于等待
 *          状态。而在Fork/Join框架实现中，如果某个子问题由于等待另一个子问题的完成而无法继续执行，
 *          那么处理该子问题的线程会主动寻找其他尚为运行的子问题来执行。这种方式减少了线程的等待时间，
 *          提高了CPU的利用率
 *
 *
 * @author sherman
 */

/**
 * 使用Fork/Join思路进行大数据计算
 */
class ForJoinCalculate extends RecursiveTask<Long> {
    private long start;
    private long end;

    private static final long THRESHOLD = 130000;

    public ForJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; ++i) {
                sum += i;
            }
            return sum;
        } else {
            long mid = start + ((end - start) >> 1);
            ForJoinCalculate left = new ForJoinCalculate(start, mid);
            left.fork();
            ForJoinCalculate right = new ForJoinCalculate(mid + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}

/**
 * 比较不同并行计算所需要的时间：
 *                          ForLoop    Fork/Join        Parallel Stream
 *  THRESHOLD = 10000L    13024ms       10665ms              4441ms
 *  THRESHOLD = 127156L                 8282ms  // 每个处理器（共6个）分配65536个任务
 *  THRESHOLD = 63578L                  12449ms
 *
 *  ---------------------------------------------------------------
 *  自己实现的Fork/Join框架相比Java8中并行流慢一倍
 * @author sherman
 */
public class ParallelStreamTest {
    private static final long start = 0L;
    private static final long end = 50000000000L;

    /**
     * 直接使用for循环测试
     */
    @Test
    public void forLoopTest(){
        Instant now = Instant.now();
        long sum = 0L;
        for (long i = start; i <= end; ++i) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(now, end).toMillis());
    }

    /**
     * 自己实现的Fork/Join测试
     */
    @Test
    public void forkJoinTest() {
        Instant now = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForJoinCalculate task = new ForJoinCalculate(start, end);
        System.out.println(pool.invoke(task));
        Instant end = Instant.now();
        System.out.println(Duration.between(now, end).toMillis());
    }

    /**
     * 使用Java8自带的并行流测试
     */
    @Test
    public void java8ParallelTest(){
        Instant now = Instant.now();
        long sum = LongStream.rangeClosed(start, end)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(now, end).toMillis());
    }
}
