package ninjasul.springmvc.application.handler.sessionattribute;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Log4j2
public class VisitTimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();

        if( httpSession.getAttribute("visitTime") == null ) {
            httpSession.setAttribute("visitTime", LocalDateTime.now());
            log.info( "visitTime: {}", httpSession.getAttribute("visitTime"));
        }

        return true;
    }
}