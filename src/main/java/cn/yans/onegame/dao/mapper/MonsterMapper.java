package cn.yans.onegame.dao.mapper;

import cn.yans.onegame.entity.Monster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MonsterMapper {

    Monster getMonster(@Param("id") String id);
}
