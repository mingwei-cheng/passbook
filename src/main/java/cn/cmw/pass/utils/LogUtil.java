package cn.cmw.pass.utils;

import cn.cmw.pass.entity.SysLogs;

import java.util.Date;
import java.util.UUID;

/**
 * @author Cheng
 * @create 2019-10-22 19:14
 **/
public class LogUtil {

    public static SysLogs setLog(String ip, String operator, short status, String remark) {
        SysLogs sysLogs = new SysLogs();
        sysLogs.setId(UUID.randomUUID().toString());
        sysLogs.setDateTime(new Date());
        sysLogs.setIp(ip);
        sysLogs.setOperation(operator);
        sysLogs.setStatus(status);
        sysLogs.setRemark(remark);
        return sysLogs;
    }
}
