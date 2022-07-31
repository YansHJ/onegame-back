package cn.yans.onegame.conmon.enumpkg;

import lombok.Getter;

@Getter
public enum RespMsg {
    SUCCESS(200,"成功"),
    FIAL(400,"失败"),
    ERROR(500,"错误");

    private final Integer code;
    private final String msg;

    RespMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}