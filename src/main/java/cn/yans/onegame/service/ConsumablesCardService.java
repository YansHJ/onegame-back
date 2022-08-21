package cn.yans.onegame.service;

import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.HealResultVO;
import cn.yans.onegame.entity.PlayerRole;


public interface ConsumablesCardService {

    HealResultVO healCard(PlayerRole role, BaseCard card);
}
