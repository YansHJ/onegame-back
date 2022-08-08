package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.UUIDUtils;
import cn.yans.onegame.dao.mapper.RoleMapper;
import cn.yans.onegame.entity.PlayerAttribute;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.service.PlayerRoleService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        attribute.setMaxHealth(10L);
        attribute.setBaseArmor(0L);
        attribute.setId(UUIDUtils.get16Uuid());
        role.setAttributeId(attribute.getId());
        //初始卡组
        List<String> cards = new ArrayList<>();
        cards.add("9cff6044aaa95fa3");
        cards.add("b314b504cea921fb");
        role.setCards(cards);
        role.setLayer(1);
        //游客存Redis,存在7天 | 注册用户存数据库&Redis 14天，登录续期
        if (role.getType() == 0){
            role.setAttribute(attribute);
            redisTemplate.opsForValue().set("role:" + role.getId(), JSON.toJSONString(role),7, TimeUnit.DAYS);
        }else {
            roleMapper.addRole(role);
            roleMapper.addAttribute(attribute);
            role.setAttribute(attribute);
            redisTemplate.opsForValue().set("role:" + role.getId(), JSON.toJSONString(role),14, TimeUnit.DAYS);
        }
        return role;
    }

    @Override
    public PlayerRole getRole(String id) {
        //TODO 后期判断是否为注册角色，注册角色先查Redis，为空再查库
        String roleJson = redisTemplate.opsForValue().get("role:" + id);
        if (StringUtils.isBlank(roleJson)){
            return new PlayerRole();
        }
        return JSON.parseObject(roleJson, PlayerRole.class);
    }

    @Override
    public PlayerRole roleAddCard(String roleId,String cardId) {
        String roleJson = redisTemplate.opsForValue().get("role:" + roleId);
        if (StringUtils.isBlank(roleJson)){
            return null;
        }
        PlayerRole role = JSON.parseObject(roleJson, PlayerRole.class);
        List<String> cards = role.getCards();
        cards.add(cardId);
        role.setCards(cards);
        redisTemplate.opsForValue().set("role:"+roleId,JSON.toJSONString(role),8,TimeUnit.DAYS);
        return role;
    }

    @Override
    public PlayerRole updateLayer(String roleId) {
        String roleJson = redisTemplate.opsForValue().get("role:" + roleId);
        if (StringUtils.isBlank(roleJson)){
            return null;
        }
        PlayerRole role = JSON.parseObject(roleJson, PlayerRole.class);
        role.setLayer(role.getLayer() + 1);
        redisTemplate.opsForValue().set("role:"+roleId,JSON.toJSONString(role),8,TimeUnit.DAYS);
        return role;
    }
}
