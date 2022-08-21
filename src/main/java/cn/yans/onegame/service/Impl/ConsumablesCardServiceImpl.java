package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.RoleCacheUtils;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.HealResultVO;
import cn.yans.onegame.entity.PlayerAttribute;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.service.ConsumablesCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 一次性卡牌处理
 */
@Service
public class ConsumablesCardServiceImpl implements ConsumablesCardService {
   @Autowired
   private RoleCacheUtils roleCacheUtils;


    @Override
    public HealResultVO healCard(PlayerRole role, BaseCard card) {
        String identifier = card.getIdentifier();
        switch (identifier){
            //参悟
            case "2ed67a24109b3df8":
                return realize(role,card);

        }
        return null;
    }

    /**
     * 参悟，永久提升血量上限7点
     */
    private HealResultVO realize(PlayerRole role, BaseCard card){
        PlayerAttribute attribute = role.getAttribute();
        attribute.setMaxHealth(attribute.getMaxHealth() + 7);
        attribute.setBaseHealth(attribute.getBaseHealth() + 7);
        role.setAttribute(attribute);
        roleCacheUtils.initRole(role,7L);
        HealResultVO healResultVO = new HealResultVO();
        healResultVO.setCard(card);
        healResultVO.setRole(role);
        return healResultVO;
    }
}
