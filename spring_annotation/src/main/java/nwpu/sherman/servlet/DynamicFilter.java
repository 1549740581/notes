package nwpu.sherman.servlet;


import javax.servlet.*;
import java.io.IOException;

/**
 * DynamicFilter：注意这个Filter是动态注册的，没有@WebFilter注解
 *
 * @author sherman
 */
public class DynamicFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("DynamicFilter...doFilter...");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
