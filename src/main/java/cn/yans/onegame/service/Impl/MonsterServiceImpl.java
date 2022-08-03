package cn.yans.onegame.service.Impl;

import cn.yans.onegame.dao.mapper.MonsterMapper;
import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterServiceImpl implements MonsterService {

    @Autowired
    private MonsterMapper monsterMapper;
    @Override
    public Monster getMonster(String id) {
        return monsterMapper.getMonster(id);
    }
}
