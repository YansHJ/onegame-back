package cn.yans.onegame.entity;

import lombok.Data;

@Data
public class HealResultVO {
    private String resultCode;
    private String msg;
    private PlayerRole role;
    private Monster monster;
    private Long finalHealValue;
    private BaseCard card;
}
