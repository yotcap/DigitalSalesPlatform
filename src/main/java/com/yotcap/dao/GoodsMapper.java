package com.yotcap.dao;

import com.yotcap.pojo.Goods;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsMapper {

    public Goods getGoodsById(Integer id);
}
