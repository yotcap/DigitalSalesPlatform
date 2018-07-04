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

//    默认code=0表示成功
    public Result(T data) {
        this.code=0;
        this.msg="success";
        this.data = data;
    }

    public Result(CodeMsg codeMsg) {
        if (codeMsg == null){
            return;
        }
        this.code = codeMsg.getCode();
        this.msg= codeMsg.getMsg();
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

    public static <T> Result<T> error(CodeMsg codeMsg){
        return new Result<>(codeMsg);
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
