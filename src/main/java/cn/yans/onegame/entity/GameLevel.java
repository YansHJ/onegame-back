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

    private List<GameLevel> nextGameLevel;
    private List<GameLevel> lastGameLevel;
}
