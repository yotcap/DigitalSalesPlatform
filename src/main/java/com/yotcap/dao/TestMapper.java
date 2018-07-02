package com.yotcap.dao;


import com.yotcap.pojo.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {

    public Test queryByUserId(Integer id);
}
