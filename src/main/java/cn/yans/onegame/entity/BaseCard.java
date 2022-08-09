package cn.yans.onegame.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BaseCard {

    /**
     * 自增主键ID
     */
    private int id;
    /**
     * UUID唯一
     */
    private String identifier;
    /**
     * 卡牌类型
     */
    private int type;
    /**
     * 卡牌品级
     */
    private int level;
    /**
     * 基础数值
     */
    private Long value;
    /**
     * 数值类型
     */
    private int valueType;
    /**
     * 加成后最终数值
     */
    private double finalValue;
    /**
     * 创建时间
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
    /**
     * 名称
     */
    private String name;
    /**
     * 逻辑删除
     * 0存在1删除
     */
    private int delFlag;
    /**
     * 概率
     */
    private int probability;
    /**
     * 描述
     */
    private String describe;
    /**
     * 费用
     */
    private int price;
    /**
     * 卡牌颜色(根据品级)
     */
    private String color;
}
