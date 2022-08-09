package cn.yans.onegame.service.Impl;

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
    @Override
    public PlayerRole buy(String roleId, int price) {
        String roleJson = redisTemplate.opsForValue().get("role:" + roleId);
        if (StringUtils.isBlank(roleJson)){
            return null;
        }
        PlayerRole role = JSON.parseObject(roleJson, PlayerRole.class);
        if (role.getBalance() <= 0){
            return null;
        }
        if (role.getBalance() - price < 0){
            return null;
        }
        role.setBalance(role.getBalance() - price);
        redisTemplate.opsForValue().set("role:"+roleId,JSON.toJSONString(role),8, TimeUnit.DAYS);
        return role;
    }
}
