package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.CardCacheUtils;
import cn.yans.onegame.common.utils.MonsterCacheUtils;
import cn.yans.onegame.common.utils.RoleCacheUtils;
import cn.yans.onegame.dao.mapper.CardMapper;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.service.ConsumablesCardService;
import cn.yans.onegame.service.TradeService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RoleCacheUtils roleCacheUtils;
    @Autowired
    private MonsterCacheUtils monsterCacheUtils;
    @Autowired
    private CardCacheUtils cardCacheUtils;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private ConsumablesCardService consumablesCardService;


    @Override
    public PlayerRole buy(String roleId, String cardId) {
        PlayerRole role = roleCacheUtils.getRole(roleId);
        BaseCard card = new BaseCard();
        if (cardId.equals("9999")){
            card.setPrice(2);
            card.setCardValueType(2);
        }else {
            card = cardMapper.getById(cardId);
        }
        if (null == role){
            return null;
        }
        if (role.getBalance() <= 0){
            return null;
        }
        if (role.getBalance() - card.getPrice() < 0){
            return null;
        }
        if (card.getCardValueType() == 3 || card.getType() == 2){
            consumablesCardService.healCard(role,card);
        }
        role.setBalance(role.getBalance() - card.getPrice());
        roleCacheUtils.initRole(role,7L);
        return role;
    }
}
