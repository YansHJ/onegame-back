package cn.yans.onegame.service.Impl;

import cn.yans.onegame.entity.*;
import cn.yans.onegame.service.SpecialCardService;
import org.springframework.stereotype.Service;

@Service
public class SpecialCardServiceImpl implements SpecialCardService {

    @Override
    public AttackResultVO attackCard(Monster monster, PlayerRole role, BaseCard card) {
        String identifier = card.getIdentifier();
        switch (identifier){
            //血祭
            case "2b29c8042fa8ebd6":
                return bloodSacrifice(monster,role,card);

        }
        return null;
    }

    /**
     * 血祭
     * 献祭自己50%血量上限的生命,对敌方造成自己血量上限2倍的伤害,无视护甲(包括自己)
     */
    private AttackResultVO bloodSacrifice(Monster monster, PlayerRole role, BaseCard card){
        AttackResultVO attackResultVO = new AttackResultVO();
        PlayerAttribute attribute = role.getAttribute();
        long baseAttack = attribute.getMaxHealth();
        if (attribute.getBaseHealth() - (baseAttack/2) <= 0){
            attribute.setBaseHealth(0L);
            role.setAttribute(attribute);
            attackResultVO.setResultCode("999");
            attackResultVO.setMsg("邪恶的声音:献祭自己的生命！但是已经没有用了...");
            attackResultVO.setCard(card);
            attackResultVO.setMonster(monster);
            attackResultVO.setRole(role);
            return attackResultVO;
        }
        attribute.setBaseHealth(attribute.getBaseHealth() - (baseAttack/2));
        role.setAttribute(attribute);
        if (monster.getBaseHealth() - (baseAttack * 2) <= 0){
            attackResultVO.setResultCode("666");
            attackResultVO.setMsg("邪恶的声音:再多给我一点血！");
            monster.setBaseHealth(0L);
        }
        monster.setBaseHealth(monster.getBaseHealth() - (baseAttack * 2));
        attackResultVO.setCard(card);
        attackResultVO.setMonster(monster);
        attackResultVO.setRole(role);
        return attackResultVO;
    }
}
