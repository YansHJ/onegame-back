package cn.yans.onegame.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CardCacheUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;
}
