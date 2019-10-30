package cn.cmw.pass.common;

import cn.cmw.pass.entity.SysUser;
import cn.cmw.pass.pojo.ApiResponse;
import cn.cmw.pass.utils.IpUtil;
import cn.cmw.pass.utils.JsonUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Cheng
 * @create 2019-10-22 19:36
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = (String) request.getSession().getAttribute("token");
        SysUser user = (SysUser) request.getSession().getAttribute("user");

        //如果session中没有user，表示没登陆
        ApiResponse apiResponse = new ApiResponse();
        if (token == null || user == null) {
            apiResponse.setCode(500);
            apiResponse.setUrl("/login.html");
            apiResponse.setMsg("用户未登录");
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json");
            response.getWriter().write(JsonUtil.objectToJson(apiResponse));
            response.sendRedirect("/login.html");
            return false;
        } else {
            String ipAddr = IpUtil.getIpAddr(request);
            if (user.getLastLoginIp().equals(ipAddr)) {
                //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
                request.getSession().setMaxInactiveInterval(60 * 30);
                return true;
            } else {
                apiResponse.setCode(500);
                apiResponse.setUrl("/login.html");
                apiResponse.setMsg("当前ip与登录ip不符");
                //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
                //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Type", "application/json");
                response.getWriter().write(JsonUtil.objectToJson(apiResponse));
                response.sendRedirect("/login.html");
                return false;
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
