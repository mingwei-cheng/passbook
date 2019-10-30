package cn.cmw.pass.controller;

import java.util.UUID;

/**
 * @author Cheng
 * @create 2019-10-22 14:51
 **/

import cn.cmw.pass.utils.IpUtil;
import cn.cmw.pass.utils.LogUtil;
import cn.cmw.pass.entity.SysUser;
import cn.cmw.pass.pojo.ApiResponse;
import cn.cmw.pass.service.LogsService;
import cn.cmw.pass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Cheng
 * @create 2019-10-22 16:19
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LogsService logsService;

    @PostMapping("/login")
    @ResponseBody
    public ApiResponse userLogin(@RequestBody SysUser user, HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        SysUser sysUser = userService.getUser(user);
        ApiResponse apiResponse = new ApiResponse();
        if (sysUser != null) {
            sysUser.setPassword(null);
            sysUser.setLastLoginTime(new Date());
            sysUser.setLastLoginIp(ipAddr);
            userService.updateUser(sysUser);
            apiResponse.setCode(200);
            apiResponse.setData(sysUser);
            apiResponse.setMsg("登录成功");
            request.getSession().setAttribute("token", UUID.randomUUID().toString());
            request.getSession().setAttribute("user", sysUser);
            request.getSession().setMaxInactiveInterval(60 * 30);
            logsService.insertLog(LogUtil.setLog(ipAddr, "/login", (short) 1, "登录成功"));
        } else {
            apiResponse.setCode(500);
            apiResponse.setData(new SysUser());
            apiResponse.setMsg("登录失败");
            logsService.insertLog(LogUtil.setLog(ipAddr, "/login", (short) 2, "登录失败"));
        }
        return apiResponse;
    }

    @PostMapping("/register")
    @ResponseBody
    public ApiResponse userRegister(@RequestBody SysUser user, HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        SysUser sysUser = userService.getUser(user);
        ApiResponse apiResponse = new ApiResponse();
        if (sysUser != null) {
            apiResponse.setCode(500);
            apiResponse.setMsg("用户已存在");
            logsService.insertLog(LogUtil.setLog(ipAddr, "/register", (short) 2, "注册失败，用户已存在"));
        } else {
            user.setCreateTime(new Date());
            user.setCreateIp(ipAddr);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(ipAddr);
            user.setId(UUID.randomUUID().toString());
            if (userService.insertUser(user)) {
                apiResponse.setCode(200);
                apiResponse.setData(sysUser);
                apiResponse.setMsg("注册成功");
                logsService.insertLog(LogUtil.setLog(ipAddr, "/register", (short) 1, "注册成功"));
            } else {
                apiResponse.setCode(500);
                apiResponse.setMsg("注册失败");
                logsService.insertLog(LogUtil.setLog(ipAddr, "/register", (short) 2, "注册失败"));
            }
        }
        return apiResponse;
    }

    @PostMapping("/checkUser")
    @ResponseBody
    public ApiResponse checkUser(@RequestParam(name = "username") String userName, HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        ApiResponse apiResponse = new ApiResponse();
        SysUser sysUser = userService.checkUserName(userName);
        if (sysUser != null) {
            apiResponse.setCode(500);
            apiResponse.setMsg("用户名已存在");
            logsService.insertLog(LogUtil.setLog(ipAddr, "/checkUser", (short) 2, "不可以注册，用户名已存在，本次查询用户名：" + userName));
        } else {
            apiResponse.setCode(200);
            apiResponse.setMsg("可以注册");
            logsService.insertLog(LogUtil.setLog(ipAddr, "/checkUser", (short) 1, "可以注册，本次查询用户名：" + userName));
        }
        return apiResponse;
    }
}
