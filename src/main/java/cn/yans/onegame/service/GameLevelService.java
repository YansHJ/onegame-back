package cn.yans.onegame.service;

import cn.yans.onegame.entity.GameLevel;

import java.util.List;

public interface GameLevelService {

    GameLevel getMap(String firstNum);
    List<List<GameLevel>> nextMap(String firstNum);
    List<GameLevel> initRandomMap(String roleId);
    List<List<GameLevel>> initRandomMap();
}
