package cn.yans.onegame.common.utils;

import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.entity.PlayerRole;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MonsterCacheUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 创建/初始化/更新怪物
     * @param monster 怪物
     * @param time 过期时间/天
     */
    public void initMonster(Monster monster,PlayerRole role, Long time){
        redisTemplate.opsForValue().set("monster:" + role.getId() + "-" + monster.getId(), JSON.toJSONString(monster),time, TimeUnit.HOURS);
    }

    /**
     * 重载
     */
    public void initMonster(Monster monster,String roleId, Long time){
        redisTemplate.opsForValue().set("monster:" + roleId + "-" + monster.getId(), JSON.toJSONString(monster),time, TimeUnit.HOURS);
    }

    public Monster getMonster(String monsterId,String roleId){
        String monsterJson = redisTemplate.opsForValue().get("monster:" + roleId + "-" + monsterId);
        if (StringUtils.isBlank(monsterJson)){
            return null;
        }
        return JSON.parseObject(monsterJson, Monster.class);
    }
    /**
     * 删除怪物
     * @param monster 怪物
     */
    public void deleteMonster(Monster monster,PlayerRole role){
        redisTemplate.delete("monster:" + role.getId() + "-" + monster.getId());
    }
}
