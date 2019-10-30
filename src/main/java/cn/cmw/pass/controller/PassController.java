package cn.cmw.pass.controller;

import java.lang.ref.Reference;

import java.util.List;

import cn.cmw.pass.entity.SysUser;
import cn.cmw.pass.entity.WebPass;
import cn.cmw.pass.pojo.ApiResponse;
import cn.cmw.pass.service.LogsService;
import cn.cmw.pass.service.PassService;
import cn.cmw.pass.utils.CipherUtil;
import cn.cmw.pass.utils.IpUtil;
import cn.cmw.pass.utils.LogUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;


/**
 * @author Cheng
 * @create 2019-10-22 19:19
 **/
@Controller
public class PassController {

    @Autowired
    private PassService passService;
    @Autowired
    private LogsService logsService;

    @RequestMapping("/getAllPass")
    @ResponseBody
    public ApiResponse getAllPass(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "limit", defaultValue = "10") int limit, HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        ApiResponse apiResponse = new ApiResponse();
        PageHelper.startPage(page, limit);
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        List<WebPass> webPasses = passService.getAllPass(user.getId());
        if (webPasses != null && webPasses.size() > 0) {
            for (WebPass webPass : webPasses) {
                String password = webPass.getPassword();
                SecretKey key = CipherUtil.generateDESKey(user.getId());
                webPass.setPassword(CipherUtil.DESDecrypt(password, key));
                if ("".equals(webPass.getSecretAnswer())) {
                    webPass.setSecretAnswer("无");
                }
                if ("".equals(webPass.getSecretQuestion())) {
                    webPass.setSecretQuestion("无");
                }
            }
            PageInfo<WebPass> pageInfo = new PageInfo<>(webPasses);
            apiResponse.setData(pageInfo);
            apiResponse.setCode(200);
            apiResponse.setMsg("获取成功");
            logsService.insertLog(LogUtil.setLog(ipAddr, "/getAllPass", (short) 1, "获取数据成功，返回" + webPasses.size() + "条数据"));
        } else {
            apiResponse.setCode(200);
            apiResponse.setMsg("用户无记录");
            logsService.insertLog(LogUtil.setLog(ipAddr, "/getAllPass", (short) 2, "获取数据成功，用户无记录"));
        }
        return apiResponse;
    }

    @PostMapping("/insertPass")
    @ResponseBody
    public ApiResponse insertPass(@RequestBody WebPass webPass, HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        ApiResponse apiResponse = new ApiResponse();
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        SecretKey key = CipherUtil.generateDESKey(user.getId());
        webPass.setPassword(CipherUtil.DESEncrypt(webPass.getPassword(), key));
        webPass.setUserId(user.getId());
        boolean re = passService.insertPass(webPass);
        if (re) {
            apiResponse.setCode(200);
            apiResponse.setMsg("新增成功");
            logsService.insertLog(LogUtil.setLog(ipAddr, "/insertPass", (short) 1, "新增成功，记录对应用户id为：" + user.getId()));
        } else {
            apiResponse.setCode(500);
            apiResponse.setMsg("新增失败");
            logsService.insertLog(LogUtil.setLog(ipAddr, "/insertPass", (short) 2, "新增失败，入库失败"));
        }
        return apiResponse;
    }

    @RequestMapping("/modifyPass")
    @ResponseBody
    public ApiResponse modifyPass(@RequestBody WebPass webPass, HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        ApiResponse apiResponse = new ApiResponse();
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        WebPass webPassByDataBase = passService.getWebPass(webPass.getId());
        if (!webPassByDataBase.getUserId().equals(user.getId())) {
            apiResponse.setCode(500);
            apiResponse.setMsg("修改失败");
            logsService.insertLog(LogUtil.setLog(ipAddr, "/modifyPass", (short) 2, "修改失败，记录对应用户id为：" + webPassByDataBase.getUserId() + ",请求用户id为：" + user.getId()));
        } else {
            SecretKey key = CipherUtil.generateDESKey(user.getId());
            webPass.setPassword(CipherUtil.DESEncrypt(webPass.getPassword(), key));

            boolean re = passService.modifyPass(webPass);
            if (re) {
                apiResponse.setCode(200);
                apiResponse.setMsg("修改成功");
                logsService.insertLog(LogUtil.setLog(ipAddr, "/modifyPass", (short) 1, "修改成功，记录对应用户id为：" + webPassByDataBase.getUserId()));
            } else {
                apiResponse.setCode(500);
                apiResponse.setMsg("修改失败");
                logsService.insertLog(LogUtil.setLog(ipAddr, "/modifyPass", (short) 2, "修改失败，入库失败"));
            }
        }

        return apiResponse;
    }

    @RequestMapping("/delPass")
    @ResponseBody
    public ApiResponse delPass(@RequestBody WebPass webPass, HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        ApiResponse apiResponse = new ApiResponse();
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        WebPass webPassByDataBase = passService.getWebPass(webPass.getId());
        if (!webPassByDataBase.getUserId().equals(user.getId())) {
            apiResponse.setCode(500);
            apiResponse.setMsg("删除失败");
            logsService.insertLog(LogUtil.setLog(ipAddr, "/delPass", (short) 2, "删除失败，记录对应用户id为：" + webPassByDataBase.getUserId() + ",请求用户id为：" + user.getId()));
        } else {
            if (passService.delPassById(webPass.getId())) {
                apiResponse.setCode(200);
                apiResponse.setMsg("删除成功");
                logsService.insertLog(LogUtil.setLog(ipAddr, "/modifyPass", (short) 1, "删除成功，记录对应用户id为"));
            } else {
                apiResponse.setCode(500);
                apiResponse.setMsg("删除失败");
                logsService.insertLog(LogUtil.setLog(ipAddr, "/modifyPass", (short) 2, "删除失败，删库失败"));
            }
        }

        return apiResponse;
    }
}
