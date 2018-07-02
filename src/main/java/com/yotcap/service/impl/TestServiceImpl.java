package com.yotcap.service.impl;

import com.yotcap.dao.TestMapper;
import com.yotcap.pojo.Test;
import com.yotcap.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public Test queryByUserId(Integer id) {
        return testMapper.queryByUserId(id);
    }
}
