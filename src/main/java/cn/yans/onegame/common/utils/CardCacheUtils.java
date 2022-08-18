package cn.yans.onegame.common.utils;

import cn.yans.onegame.entity.BaseCard;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class CardCacheUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;
    public static final String ROLE_KEY = "card:role:";
    public static final String DRAW_KEY = "card:draw:";

    public List<BaseCard> getRoleCard(String roleId){
        String result = redisTemplate.opsForValue().get(ROLE_KEY + roleId);
        if (StringUtils.isBlank(result)){
            return null;
        }
        return  JSONObject.parseArray(result, BaseCard.class);
    }

    public void setRoleCard(List<BaseCard> cards,String roleId){
        redisTemplate.opsForValue().set(ROLE_KEY + roleId, JSON.toJSONString(cards),20, TimeUnit.MINUTES);
    }

    public void deleteRoleCard(String roleId){
        redisTemplate.delete(ROLE_KEY + roleId);
    }

    public List<BaseCard> getDrawCard(String roleId){
        String result = redisTemplate.opsForValue().get(DRAW_KEY + roleId);
        if (StringUtils.isBlank(result)){
            return null;
        }
        return  JSONObject.parseArray(result, BaseCard.class);
    }

    public void setDrawCard(List<BaseCard> cards,String roleId){
        redisTemplate.opsForValue().set(DRAW_KEY + roleId, JSON.toJSONString(cards),20, TimeUnit.MINUTES);
    }

    public void deleteDrawCard(String roleId){
        redisTemplate.delete(DRAW_KEY + roleId);
    }
}
