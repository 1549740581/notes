package nwpu.sherman.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 自定义视图
 * 该视图通过@Component加入到IOC容器中，并制定视图名称为customView
 * 之后Controller直接返回的就是该视图的名称，如果找不到，则尝试使用InternalResourceViewResolver继续解析
 *
 * @author sherman
 */
@Component("customView")
public class CustomView implements View {
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("custom view");
        response.getWriter().print("custom view, time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
    }
}
