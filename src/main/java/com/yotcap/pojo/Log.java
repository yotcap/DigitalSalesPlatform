package com.yotcap.pojo;

public class Log {
    private Integer id;
    private String accountName;
    private String url;
    private String operation;
    private String desc;
    private String result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", url='" + url + '\'' +
                ", operation='" + operation + '\'' +
                ", desc='" + desc + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
