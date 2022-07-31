package cn.yans.onegame.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseCard {

    private int id;
    private int type;
    private int level;
    private double value;
    private int valueType;
    private double finalValue;
    private String createTime;
    private String name;
}
