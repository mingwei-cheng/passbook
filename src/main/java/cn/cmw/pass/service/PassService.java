package cn.cmw.pass.service;

import cn.cmw.pass.entity.WebPass;

import java.util.List;

/**
 * @author Cheng
 * @create 2019-10-22 20:12
 **/
public interface PassService {
    List<WebPass> getAllPass(String userId);

    WebPass getWebPass(String id);

    boolean delPassById(String id);

    boolean modifyPass(WebPass webPass);

    boolean insertPass(WebPass webPass);
}
