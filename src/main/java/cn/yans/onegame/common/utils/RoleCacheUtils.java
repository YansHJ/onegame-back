package cn.yans.onegame.common.utils;

import cn.yans.onegame.entity.PlayerRole;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RoleCacheUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 创建/初始化/更新角色
     * @param role 角色
     * @param time 过期时间/天
     */
    public void initRole(PlayerRole role, Long time){
        redisTemplate.opsForValue().set("role:" + role.getId(), JSON.toJSONString(role),time, TimeUnit.DAYS);
    }

    /**
     * 通过roleId获取角色
     * @param roleId 角色id
     * @return 角色
     */
    public PlayerRole getRole(String roleId){
        String roleJson = redisTemplate.opsForValue().get("role:" + roleId);
        if (StringUtils.isBlank(roleJson)){
            return null;
        }
        return JSON.parseObject(roleJson, PlayerRole.class);
    }

    /**
     * 删除角色
     * @param role 角色
     */
    public void deleteRole(PlayerRole role){
        redisTemplate.delete("role:" + role.getId());
    }
}
