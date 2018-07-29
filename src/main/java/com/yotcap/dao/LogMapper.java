package com.yotcap.dao;

import com.yotcap.pojo.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMapper {

    /*添加用户*/
    int addLog(@Param("log") Log log);

}
