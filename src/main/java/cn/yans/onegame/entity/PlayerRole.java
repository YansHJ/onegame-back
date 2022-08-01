package cn.yans.onegame.entity;

import lombok.Data;

@Data
public class PlayerRole {

    private String id;

    private String name;

    /**
     * 角色类型(0 游客临时，1 注册)
     */
    private int type;

    private String attributeId;

    private int userId;

    private String createTime;

    /**
     * 0男，1女
     */
    private int sex;

    private PlayerAttribute attribute;
}
