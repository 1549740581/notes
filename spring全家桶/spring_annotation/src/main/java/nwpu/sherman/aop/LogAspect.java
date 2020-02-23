package nwpu.sherman.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 切面类
 * 该切面类的几个通知方法会切入到MathCalculator类的div方法各个部分
 *
 * @author sherman
 */

@Aspect
public class LogAspect {

    /**
     * 抽取出公共的切入点表达式，之后引用时可以简化：
     * 1、本类中引用：直接使用抽取出的方法名
     * 2、外部类中使用：全限定类名.抽取的方法名
     */
    @Pointcut("execution(public int nwpu.sherman.aop.MathCalculator.div(int, int))")
    public void pointcut() {

    }

    /**
     * 不使用抽取的公共切入点表达式，会比较复杂
     */
    @Before("execution(public int nwpu.sherman.aop.MathCalculator.*(..))")
    public void startLog(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("@Before：参数列表：{" + Arrays.asList(args) + "}");
    }

    /**
     * 模拟在类外部引用抽取出来的pointcut，因为当前是在类内部，这样使用也没问题
     */
    @After("nwpu.sherman.aop.LogAspect.pointcut()")
    public void afterLog() {
        System.out.println("@After:----------------");
    }

    /**
     * 1、直接使用抽取出来的切入点表达式
     * 2、如果要获取切入点方法的返回值，需要指定returning属性接受，同时参数中也要声明
     * 两者名称要相同
     * 3、如果要指定返回值且入参有多个时，JoinPoint必须在参数第一位！
     */
    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturningLog(JoinPoint joinPoint, int result) {
        System.out.println("[" + joinPoint.getSignature().getName() + "]");
        System.out.println("@AfterReturning: -----, result: " + result);
    }

    /**
     * 类似的，如果要捕获异常信息，也需要指定throwing，方法入参中也要相同
     */
    @AfterThrowing(value = "pointcut()", throwing = "exception")
    public void  afterThrowingLog(Exception exception) {
        System.out.println("@AfterThrowing：-----, exception：" + exception);
    }
}
