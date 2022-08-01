package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.UUIDUtils;
import cn.yans.onegame.dao.mapper.RoleMapper;
import cn.yans.onegame.entity.PlayerAttribute;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.service.PlayerRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayerRoleServiceImpl implements PlayerRoleService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PlayerRole initRole(PlayerRole playerRole) {
        PlayerRole role = new PlayerRole();
        role.setSex(playerRole.getSex());
        role.setName("王二狗");
        if (!StringUtils.isBlank(playerRole.getName())){
            role.setName(playerRole.getName());
        }
        role.setType(playerRole.getType());
        role.setUserId(0);
        if (0 != playerRole.getType()){
            role.setUserId(playerRole.getUserId());
        }
        role.setId(UUIDUtils.get16Uuid());
        //属性初始化
        PlayerAttribute attribute = new PlayerAttribute();
        attribute.setBaseAttack(10L);
        attribute.setBaseHealth(10L);
        attribute.setBaseArmor(0L);
        attribute.setId(UUIDUtils.get16Uuid());
        role.setAttributeId(attribute.getId());
        roleMapper.addRole(role);
        roleMapper.addAttribute(attribute);
        role.setAttribute(attribute);
        return role;
    }
}
