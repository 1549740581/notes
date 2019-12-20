package nwpu.sherman.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;

/**
 * 使用DeferredResult完成异步处理工作：
 * 应用1：接受请求 ----> 消息中间件 ----应用2：异步处理
 *
 * 1、应用1接受到请求，但是并不能处理，而是将请求创建订单的消息放入DeferredResultQueue（模拟消息中间件）中
 * 2、DeferredResultQueue中的消息如果3秒内没有被处理，则抛出异常
 * 3、应用2从DeferredResultQueue中拿到待处理的消息，并进行异步处理
 *
 * @author sherman
 */
@Controller
public class AsyncDeferredResultServlet {

    /**
     * 模拟应用1：接受生成订单的请求，但是该应用不能自己生成应用，将消息放入到DeferredResultQueue中
     * 应用启动后，先访问/createOrder，请求生成订单
     */
    @RequestMapping("/createOrder")
    @ResponseBody
    public DeferredResult<Object> createOrder() {
        DeferredResult<Object> deferredResult = new DeferredResult<>(3000L, "time out...");
        DeferredResultQueue.save(deferredResult);
        return deferredResult;
    }

    /**
     * 模拟应用2：从DeferredResultQueue中拿到订单生成消息，并异步处理
     * 应用启动后，先访问/createOrder，然后在3秒内访问/create，则能成功执行异步请求，返回结果
     */
    @ResponseBody
    @RequestMapping("/create")
    public String create() {
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        deferredResult.setResult(order);
        return "success ===>" + order;
    }
}
