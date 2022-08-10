package cn.yans.onegame.service;

import cn.yans.onegame.entity.AttackResultVO;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.entity.PlayerRole;

public interface SpecialCardService {

    AttackResultVO attackCard(Monster monster, PlayerRole role, BaseCard card);
}
