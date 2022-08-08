package cn.yans.onegame.entity;

import lombok.Data;

import java.util.List;

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
    private List<String> cards;

    /**
     * 0存在，1删除
     */
    private int delFlag;

    /**
     * 当前所在层
     */
    private int layer;
}
