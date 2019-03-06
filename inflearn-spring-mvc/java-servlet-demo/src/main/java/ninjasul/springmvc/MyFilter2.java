package ninjasul.springmvc;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import java.io.IOException;

@Log4j2
public class MyFilter2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter2 - destroy");
    }
}