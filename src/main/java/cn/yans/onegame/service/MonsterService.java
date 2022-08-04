package cn.yans.onegame.service;

import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.entity.MonsterSkill;

import java.util.List;

public interface MonsterService {

    Monster getMonster(String id);
    List<MonsterSkill> getMonsterByIdList(String ids);
}
