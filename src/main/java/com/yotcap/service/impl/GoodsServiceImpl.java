package com.yotcap.service.impl;

import com.yotcap.dao.GoodsMapper;
import com.yotcap.pojo.Goods;
import com.yotcap.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    public Goods getGoodsById(Integer id){
        return goodsMapper.getGoodsById(id);
    }
}
