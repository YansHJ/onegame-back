package cn.yans.onegame.common.enumpkg;

import lombok.Getter;

@Getter
public enum RespMsg {
    SUCCESS(200,"成功"),
    FIAL(400,"失败"),
    ERROR(500,"错误"),
    DEFEATED(999,"被打败了"),
    VICTORY(666,"胜利");

    private final Integer code;
    private final String msg;

    RespMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}