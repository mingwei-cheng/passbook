package cn.cmw.pass.pojo;

import lombok.Data;

/**
 * @author Cheng
 * @create 2019-10-22 16:31
 **/
@Data
public class ApiResponse {
    String url;
    Integer code;
    String msg;
    Object data;
}
