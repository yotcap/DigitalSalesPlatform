package com.yotcap.result;

public class CodeMsg {

    private int code;
    private String msg;

//    用户模块 200
    public static final CodeMsg LOGINERROR = new CodeMsg(200,"登录失败");
    public static final CodeMsg USERNOTFOUND = new CodeMsg(201,"用户名错误");
    public static final CodeMsg PASSWORDERROR = new CodeMsg(202,"密码错误");
    public static final CodeMsg NOTLOGIN = new CodeMsg(203,"用户未登录");



    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
