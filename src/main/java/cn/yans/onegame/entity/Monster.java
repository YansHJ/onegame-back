package cn.yans.onegame.entity;

import lombok.Data;

import java.util.List;

@Data
public class Monster {

    private String id;
    private String name;
    private Long baseHealth;
    private Long maxHealth;
    private Long baseArmor;
    private int level;
    private int type;
    /**
     * 技能实体集合
     */
    private List<MonsterSkill> skill;
    /**
     * 技能id的集合字符串
     */
    private String skillId;
}
