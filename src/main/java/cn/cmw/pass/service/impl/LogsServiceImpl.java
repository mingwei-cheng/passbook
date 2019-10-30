package cn.cmw.pass.service.impl;

import cn.cmw.pass.dao.SysLogsMapper;
import cn.cmw.pass.entity.SysLogs;
import cn.cmw.pass.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cheng
 * @create 2019-10-22 18:44
 **/
@Service
public class LogsServiceImpl implements LogsService {
    @Autowired
    private SysLogsMapper sysLogsMapper;
    @Override
    public void insertLog(SysLogs logs) {
        sysLogsMapper.insertSelective(logs);
    }
}
