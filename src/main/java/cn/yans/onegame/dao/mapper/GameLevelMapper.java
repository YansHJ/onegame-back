package cn.yans.onegame.dao.mapper;

import cn.yans.onegame.entity.GameLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameLevelMapper {
    List<GameLevel> getMap(@Param("firstNum") String firstNum);
}
