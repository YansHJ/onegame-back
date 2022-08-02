package cn.yans.onegame.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PlayerAttribute {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 基础攻击
     */
    private Long baseAttack;

    /**
     * 基础生命
     */
    private Long baseHealth;

    /**
     * 血量上限
     */
    private Long maxHealth;

    /**
     * 基础护甲
     */
    private Long baseArmor;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 0存在，1删除
     */
    private int delFlag;
}
