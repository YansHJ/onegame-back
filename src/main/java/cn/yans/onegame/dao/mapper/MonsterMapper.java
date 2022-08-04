package cn.yans.onegame.dao.mapper;

import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.entity.MonsterSkill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MonsterMapper {

    Monster getMonster(@Param("id") String id);
    MonsterSkill getMonsterSkill(@Param("id")String id);
}
