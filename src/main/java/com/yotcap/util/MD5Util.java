package com.yotcap.util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    /**
     * MD5加密
     * @param src
     * @return
     */
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public static String formToDb(String formPass,String salt){
        String str = salt.charAt(0)+salt.charAt(1)+formPass;
        return md5(str);
    }

    public static void main(String[] args) {
        System.out.println(md5("111"));
    }
}
