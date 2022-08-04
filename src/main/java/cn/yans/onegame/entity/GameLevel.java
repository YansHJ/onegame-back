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

    private List<GameLevel> nextGameLevel;
    private List<GameLevel> lastGameLevel;
}
