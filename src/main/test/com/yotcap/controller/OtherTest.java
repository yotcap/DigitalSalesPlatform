package com.yotcap.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

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
//        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid);
//        long highBites = uuid.getMostSignificantBits();
//        System.out.println(highBites);
//        long lowBites = uuid.getLeastSignificantBits();
//        System.out.println(lowBites);
        System.out.println(Integer.toHexString(257));
        System.out.println(Integer.toHexString(555));
    }

    @Test
    public void getUser() {
    }
}