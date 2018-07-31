package com.yotcap.controller;

import com.yotcap.pojo.Goods;
import com.yotcap.pojo.Test;
import com.yotcap.result.Result;
import com.yotcap.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("goods/")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @CrossOrigin(origins = "*", maxAge = 60)
    @RequestMapping("/goods.do")
    @ResponseBody
    public Goods goods(){
        return goodsService.getGoodsById(1);
    }


}
