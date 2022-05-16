package cn.airbozh.utility;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ResMsg <T> {
    private int code;
    private String msg;
    private T data;

    public ResMsg() {
    }

    public ResMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResMsg(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        if(this.data!=null){
            return  "ResMsg{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", data=" + data +
                    '}';
        }else{
            return  "ResMsg{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }

    }
}
