package cn.cmw.pass.service;

import cn.cmw.pass.entity.SysUser;

/**
 * @author Cheng
 * @create 2019-10-22 14:51
 **/
public interface UserService {

    SysUser getUser(SysUser user);

    boolean insertUser(SysUser user);

    boolean updateUser(SysUser user);

    SysUser checkUserName(String userName);


}
