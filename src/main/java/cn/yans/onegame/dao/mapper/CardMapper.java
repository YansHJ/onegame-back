package cn.yans.onegame.dao.mapper;

import cn.yans.onegame.entity.BaseCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CardMapper {


    BaseCard getFromPack(@Param("probability") int probability,@Param("cards") List<String> cards);

    BaseCard getById(@Param("id") String id);

    BaseCard getFromAll(@Param("probability") int probability);
}
