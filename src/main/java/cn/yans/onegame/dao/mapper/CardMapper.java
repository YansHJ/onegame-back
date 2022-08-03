package cn.yans.onegame.dao.mapper;

import cn.yans.onegame.entity.BaseCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CardMapper {


    BaseCard getOne(@Param("probability") int probability);

    BaseCard getById(@Param("id") String id);
}
