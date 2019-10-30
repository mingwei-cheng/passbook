package cn.cmw.pass.entity;

import java.util.Date;
import lombok.Data;

@Data
public class SysLogs {
    private String id;

    private Date dateTime;

    private String ip;

    private String operation;

    /**
     * 1-成功 2-失败
     */
    private Short status;

    /**
     * 备注
     */
    private String remark;
}