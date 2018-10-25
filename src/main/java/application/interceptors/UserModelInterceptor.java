package application.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public class UserModelInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest aRequest,
                             HttpServletResponse aResponse,
                             Object aHandler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object aHandler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null) {
            Principal user = request.getUserPrincipal();
            // its allow get username in view by _user.getName()
            modelAndView.addObject("_user", user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest aRequest,
                                HttpServletResponse aResponse,
                                Object aHandler, Exception aEx) throws Exception { }
}