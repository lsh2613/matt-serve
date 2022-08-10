package mat.mat_t.web;

import mat.mat_t.web.session.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object handler) throws Exception {
//
//        String requestURI = request.getRequestURI();
//
//        HttpSession session = request.getSession(false);
//
//        if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
//
//            //로그인으로 redirect
//            response.sendRedirect("/login?redirectURL=" + requestURI);
//            return false;
//        }
//        return true;
//
//    }
}