package cn.yans.onegame.common.enumpkg;

import lombok.Data;

@Data
public class RespData<T> {

    private Integer code;

    private String msg;

    private T data;

    public RespData() {
        this.code = RespMsg.SUCCESS.getCode();
        this.msg = RespMsg.SUCCESS.getMsg();
    }

    public RespData(T data){
        this();
        this.data = data;
    }

    public RespData (String msg){
        this.code = RespMsg.SUCCESS.getCode();
        this.msg = msg;
    }

    public RespData (String msg,T data){
        this.code = RespMsg.SUCCESS.getCode();
        this.msg = msg;
        this.data = data;
    }

    public RespData<T> success(){
        this.code = RespMsg.SUCCESS.getCode();
        this.msg = RespMsg.SUCCESS.getMsg();
        return this;
    }

    public RespData<T> fail(String msg){
        this.code = RespMsg.FIAL.getCode();
        this.msg = msg;
        return this;
    }

    public RespData<T> defeated(){
        this.code = RespMsg.DEFEATED.getCode();
        this.msg = RespMsg.DEFEATED.getMsg();
        return this;
    }

    public RespData<T> victory(){
        this.code = RespMsg.VICTORY.getCode();
        this.msg = RespMsg.VICTORY.getMsg();
        return this;
    }

}