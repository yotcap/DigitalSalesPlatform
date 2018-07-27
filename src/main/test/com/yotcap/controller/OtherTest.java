package com.yotcap.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;
import java.util.UUID;

import static org.junit.Assert.*;

public class OtherTest {


    @Test
    public void test1() {
        System.out.println(Math.floor((10 - 1) / 2D));//4.0
        System.out.println((int)Math.floor((10 - 1) / 2D));//4
        System.out.println((int)Math.ceil((10 - 1) / 2D));//5
    }

    @Test
    public void Uuid() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().replace("-","").length());//32
        System.out.println(uuid.toString().length());//36
        System.out.println(uuid.getLeastSignificantBits());
        System.out.println(uuid.getMostSignificantBits());
//        long highBites = uuid.getMostSignificantBits();
//        System.out.println(highBites);
//        long lowBites = uuid.getLeastSignificantBits();
//        System.out.println(lowBites);
//        System.out.println(Integer.toHexString(257));
//        System.out.println(Integer.toHexString(555));
    }

    private void printB(byte[] randomBytes){
        for (int i=0;i<randomBytes.length;i++){
            System.out.print(randomBytes[i]+" ");
        }
        System.out.println();
    }

    @Test
    public void jsonTest() {
        JSONObject json = new JSONObject();
        json.put("status_code", 50301);
        json.put("status_text", "离开时间过长，请重新登录！");

        System.out.println(json);
    }
    @Test
    public void base64Test() {
        long outDate = System.currentTimeMillis() + 30 * 60 * 1000;//半小时
        String key = Joiner.on("#").join("kaibing", outDate);
        System.out.println(key);
        //编码
        String encode = Base64.encodeBase64URLSafeString("http://localhost:8080/req.do".getBytes());
        System.out.println(encode);
        //解码
        String decode = new String(Base64.decodeBase64(encode));
        System.out.println(decode);

    }
}