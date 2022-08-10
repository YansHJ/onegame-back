package cn.yans.onegame.service;

import cn.yans.onegame.entity.AttackResultVO;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.entity.PlayerRole;

import javax.management.relation.Role;
import java.util.Map;

public interface BattleService {

    AttackResultVO baseAttack(Monster monster, PlayerRole role, BaseCard card);
    Map<String,Object> underAttack(Monster monster, PlayerRole role);
    PlayerRole getHeal(PlayerRole role,BaseCard card);
    PlayerRole increaseArmor(PlayerRole role,BaseCard card);
}
