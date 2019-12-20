package nwpu.sherman.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支持异步请求的Servlet示例
 *
 * @author sherman
 */
@WebServlet(value = "/async", asyncSupported = true)
public class AsyncCallableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("主线程开始工作..." + System.currentTimeMillis());
        final AsyncContext asyncContext = request.startAsync();
        asyncContext.start(() -> {
            timeConsumingTask();
            asyncContext.complete();
            try {
                asyncContext.getResponse().getWriter().write("hello async...");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        System.out.println("主线程结束工作..." + System.currentTimeMillis());
    }

    /**
     * 一个耗时任务，演示异步请求工作方式
     */
    private void timeConsumingTask() {
        try {
            System.out.println("子线程开始工作..." + System.currentTimeMillis());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程结束工作..." + System.currentTimeMillis());
    }
}

