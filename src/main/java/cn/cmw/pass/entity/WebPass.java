package cn.cmw.pass.entity;

import lombok.Data;

@Data
public class WebPass {
    /**
     * UUID
     */
    private String id;

    /**
     * 网站
     */
    private String website;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 密保问题
     */
    private String secretQuestion;

    /**
     * 密保密码
     */
    private String secretAnswer;

    /**
     * 关联到用户id
     */
    private String userId;
}