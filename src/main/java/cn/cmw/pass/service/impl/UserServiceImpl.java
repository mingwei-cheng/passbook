package cn.cmw.pass.service.impl;

import cn.cmw.pass.dao.SysLogsMapper;
import cn.cmw.pass.dao.SysUserMapper;
import cn.cmw.pass.entity.SysUser;
import cn.cmw.pass.entity.SysUserExample;
import cn.cmw.pass.service.UserService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.util.List;

/**
 * @author Cheng
 * @create 2019-10-22 15:35
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUser(SysUser user) {
        if (user.getPassword() != null) {
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andUserNameEqualTo(user.getUserName()).andPasswordEqualTo(user.getPassword());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(userExample);
        if (sysUsers != null && sysUsers.size() > 0) {
            return sysUsers.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean insertUser(SysUser user) {
        return sysUserMapper.insertSelective(user) > 0;
    }

    @Override
    public boolean updateUser(SysUser user) {
        sysUserMapper.updateByPrimaryKeySelective(user);
        return false;
    }

    @Override
    public SysUser checkUserName(String userName) {
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(userExample);
        return sysUsers.size() > 0 ? sysUsers.get(0) : null;
    }


}
