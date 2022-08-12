package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.MonsterCacheUtils;
import cn.yans.onegame.common.utils.RoleCacheUtils;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.service.TradeService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RoleCacheUtils roleCacheUtils;
    @Autowired
    private MonsterCacheUtils monsterCacheUtils;


    @Override
    public PlayerRole buy(String roleId, int price) {
        PlayerRole role = roleCacheUtils.getRole(roleId);
        if (null == role){
            return null;
        }
        if (role.getBalance() <= 0){
            return null;
        }
        if (role.getBalance() - price < 0){
            return null;
        }
        role.setBalance(role.getBalance() - price);
        roleCacheUtils.initRole(role,7L);
        return role;
    }
}
