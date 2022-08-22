package cn.yans.onegame.entity;

import lombok.Data;

import java.util.List;

@Data
public class GameLevel {

    private String id;
    private String number;
    private String next;
    private String last;
    private int type;
    private String monsterIds;
    private String describe;
    private int cardNum;
    /**
     * 层数(4-1就是4层，4-2也是4层)
     */
    private int layer;

    /**
     * 金币奖励
     */
    private int goldAward;
    /**
     * 困难程度
     */
    private int difficulty;

    private int delFlag;

    private List<GameLevel> nextGameLevel;
    private List<GameLevel> lastGameLevel;
}
