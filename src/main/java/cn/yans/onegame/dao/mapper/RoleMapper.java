package cn.yans.onegame.dao.mapper;

import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.PlayerAttribute;
import cn.yans.onegame.entity.PlayerRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {

    void addRole(@Param("role")PlayerRole role);

    void addAttribute(@Param("att")PlayerAttribute attribute);


}
