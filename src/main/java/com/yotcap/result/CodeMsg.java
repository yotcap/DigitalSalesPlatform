package com.yotcap.result;

public class CodeMsg {



    private int code;
    private String msg;

    public static final CodeMsg TESTSUCCESS = new CodeMsg(110,"测试成功");
    public static final CodeMsg UNKNOWNERROR = new CodeMsg(111,"未知错误");


    //    用户模块 200
    public static final CodeMsg LOGINERROR = new CodeMsg(200,"登录失败");
    public static final CodeMsg USERNOTFOUND = new CodeMsg(201,"用户名错误");
    public static final CodeMsg PASSWORDERROR = new CodeMsg(202,"密码错误");
    public static final CodeMsg NOTLOGIN = new CodeMsg(203,"用户未登录");
    public static final CodeMsg LOGOUTSUCCESS = new CodeMsg(204,"退出成功");
    public static final CodeMsg REGISTERERROR = new CodeMsg(205,"注册失败");
    public static final CodeMsg USERNAMEEXTIS = new CodeMsg(206,"用户名已存在");
    public static final CodeMsg POSSCOULDNULL = new CodeMsg(207,"密码不能为空");
    public static final CodeMsg MSGSENDERROR = new CodeMsg(208,"短信发送失败");
    public static final CodeMsg MSGERROR = new CodeMsg(208,"验证码错误");

//

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
