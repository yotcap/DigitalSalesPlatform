package com.yotcap.service.impl;

import com.yotcap.dao.LogMapper;
import com.yotcap.pojo.Log;
import com.yotcap.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;


    @Override
    public int addLog(Log log) {
        return logMapper.addLog(log);
    }
}
