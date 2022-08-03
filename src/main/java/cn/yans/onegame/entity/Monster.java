package cn.yans.onegame.entity;

import cn.yans.onegame.entity.noindatabase.MonsterSkill;
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
     * 技能,数据库存储Json串
     */
    private List<MonsterSkill> skill;
}
