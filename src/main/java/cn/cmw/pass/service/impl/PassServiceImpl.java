package cn.cmw.pass.service.impl;

import cn.cmw.pass.dao.WebPassMapper;
import cn.cmw.pass.entity.WebPass;
import cn.cmw.pass.entity.WebPassExample;
import cn.cmw.pass.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Cheng
 * @create 2019-10-22 20:12
 **/
@Service
public class PassServiceImpl implements PassService {

    @Autowired
    private WebPassMapper webPassMapper;

    @Override
    public List<WebPass> getAllPass(String userId) {
        WebPassExample webPassExample = new WebPassExample();
        webPassExample.createCriteria().andUserIdEqualTo(userId);
        return webPassMapper.selectByExample(webPassExample);
    }

    @Override
    public WebPass getWebPass(String id) {
        return webPassMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean delPassById(String id) {
        return webPassMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean modifyPass(WebPass webPass) {
        return webPassMapper.updateByPrimaryKeySelective(webPass) > 0;
    }

    @Override
    public boolean insertPass(WebPass webPass) {
        webPass.setId(UUID.randomUUID().toString());
        return webPassMapper.insertSelective(webPass) > 0;
    }
}
