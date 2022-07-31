package cn.yans.onegame.dao.mapper;

import cn.yans.onegame.entity.BaseCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CardMapper {

    @Select("select * from (select * from ys_cardpool_attack where probability >= 20)t order by rand() limit 1;")
    BaseCard getOne(@Param("i") int i);
}
