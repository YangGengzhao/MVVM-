package com.example.baselibrary.eventBus;

public class ViewModelBean {
    private int code;
    private Object data;

    public ViewModelBean() {
    }
    public ViewModelBean( Object data) {
        this.data = data;
    }
    public ViewModelBean(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSucceed() {
        return String.valueOf(code).equals("200") ? true : false;
    }
}
