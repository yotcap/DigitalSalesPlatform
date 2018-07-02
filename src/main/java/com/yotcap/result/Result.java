package com.yotcap.result;

public class Result<T> {
/*    {
        "code":0000,
        "msg":"信息",
        "date":{}
    }
*/

    private int code;
    private String msg;
    private T data;

    public Result(T data) {
        this.code=0;
        this.msg="success";
        this.data = data;
    }

    /**
     * 成功的时候调用
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
