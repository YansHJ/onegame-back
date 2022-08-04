package cn.yans.onegame.service.Impl;

import cn.yans.onegame.dao.mapper.MonsterMapper;
import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.entity.MonsterSkill;
import cn.yans.onegame.service.MonsterService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonsterServiceImpl implements MonsterService {

    @Autowired
    private MonsterMapper monsterMapper;
    @Override
    public Monster getMonster(String id) {
        Monster monster = monsterMapper.getMonster(id);
        List<MonsterSkill> monsterByIdList = getMonsterByIdList(monster.getSkillId());
        monster.setSkill(monsterByIdList);
        return monster;
    }

    @Override
    public List<MonsterSkill> getMonsterByIdList(String ids) {
        List<MonsterSkill> monsterSkills = new ArrayList<>();
        List<String> idList = JSONObject.parseArray(ids, String.class);
        for (String id : idList) {
            MonsterSkill monsterSkill = monsterMapper.getMonsterSkill(id);
            monsterSkills.add(monsterSkill);
        }
        return monsterSkills;
    }
}
