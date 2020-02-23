package nwpu.sherman.test;

import nwpu.sherman.bean.Person;
import nwpu.sherman.config.BeanConfig;
import nwpu.sherman.singleton.*;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.*;

/**
 * @author sherman
 */
public class MainTest {
    /**
     * 饿汉式Singleton
     */
    @Test
    public void singleton01Test() {
        System.out.println(Singleton01.INSTANCE);
    }

    /**
     * 饿汉式Singleton：静态代码块形式
     */
    @Test
    public void singleton02Test() {
        System.out.println(Singleton02.INSTANCE);
    }

    /**
     * 饿汉式Singleton：枚举
     * <p>
     * 注意枚举已经重写了toString()方法，返回的不是地址值
     */
    @Test
    public void singleton03Test() {
        System.out.println(Singleton03.INSTANCE);
    }

    /**
     * 懒汉式Singleton：存在线程安全问题
     */
    @Test
    public void singleton04Test() throws ExecutionException, InterruptedException {
        //System.out.println(Singleton04.getInstance() == Singleton04.getInstance());

        Callable<Singleton04> callable = Singleton04::getInstance;
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton04> f1 = es.submit(callable);
        Future<Singleton04> f2 = es.submit(callable);
        System.out.println(f1.get() == f2.get());
    }

    /**
     * 懒汉式Singleton：使用synchronized加锁解决
     */
    @Test
    public void singleton05Test() throws ExecutionException, InterruptedException {
        Callable<Singleton05> callable = Singleton05::getInstance;
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton05> f1 = es.submit(callable);
        Future<Singleton05> f2 = es.submit(callable);
        System.out.println(f1.get() == f2.get());
    }

    /**
     * 懒汉式Singleton：DCL锁
     */
    @Test
    public void singleton06Test() throws ExecutionException, InterruptedException {
        Callable<Singleton06> callable = Singleton06::getInstance;
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton06> f1 = es.submit(callable);
        Future<Singleton06> f2 = es.submit(callable);
        System.out.println(f1.get() == f2.get());
    }

    /**
     * 懒汉式Singleton：静态内部类
     */
    @Test
    public void singleton07Test() throws ExecutionException, InterruptedException {
        Callable<Singleton07> callable = Singleton07::getInstance;
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton07> f1 = es.submit(callable);
        Future<Singleton07> f2 = es.submit(callable);
        System.out.println(f1.get() == f2.get());
    }

    /**
     * 测试Bean的作用域
     */
    @Test
    public void beanScopeTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        Person person = ((Person) context.getBean("person"));
        System.out.println(person.getName());
    }
}
