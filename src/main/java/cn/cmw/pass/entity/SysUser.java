package cn.cmw.pass.entity;

import java.util.Date;
import lombok.Data;

@Data
public class SysUser {
    /**
     * uuid
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建ip
     */
    private String createIp;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 上次登录ip
     */
    private String lastLoginIp;
}