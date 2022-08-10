package cn.yans.onegame.entity;

import lombok.Data;

@Data
public class AttackResultVO {

    private String resultCode;
    private String msg;
    private PlayerRole role;
    private Monster monster;
    private Long finalAttackValue;
    private BaseCard card;
}
