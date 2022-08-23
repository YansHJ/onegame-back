package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.MapCacheUtils;
import cn.yans.onegame.common.utils.MonsterCacheUtils;
import cn.yans.onegame.common.utils.ProbabilityUtils;
import cn.yans.onegame.common.utils.RoleCacheUtils;
import cn.yans.onegame.entity.*;
import cn.yans.onegame.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class BattleServiceImpl implements BattleService {

    @Autowired
    private RoleCacheUtils roleCacheUtils;
    @Autowired
    private MonsterCacheUtils monsterCacheUtils;
    @Autowired
    private MapCacheUtils mapCacheUtils;

    @Override
    public AttackResultVO baseAttack(Monster monster, PlayerRole role, BaseCard card) {
        AttackResultVO attackResultVO = new AttackResultVO();
        attackResultVO.setFinalAttackValue(card.getValue());
        Long monsterArmor = monster.getBaseArmor();
        if (monsterArmor - card.getValue() <= 0){
            monster.setBaseHealth((monsterArmor + monster.getBaseHealth()) - card.getValue());
            monster.setBaseArmor(0L);
        }else {
            monster.setBaseArmor(monsterArmor - card.getValue());
        }
        //打败怪物
        if (monster.getBaseHealth() <= 0){
            monsterCacheUtils.deleteMonster(monster,role);
            attackResultVO.setResultCode("666");
        }
        //更新怪物
        monsterCacheUtils.initMonster(monster,role,4L);
        attackResultVO.setCard(card);
        attackResultVO.setRole(role);
        attackResultVO.setMonster(monster);
        return attackResultVO;
    }

    @Override
    public Map<String,Object> underAttack(Monster monster, PlayerRole role) {
        List<MonsterSkill> skill = monster.getSkill();
        int probability = ProbabilityUtils.getProbability();
        //通过抽取概率获取此次攻击
        MonsterSkill monsterSkill = changeMonsterSkill(skill, probability);
        PlayerAttribute attribute = role.getAttribute();
        Long baseArmor = attribute.getBaseArmor();
        if (baseArmor - monsterSkill.getValue() <= 0) {
            attribute.setBaseHealth((attribute.getBaseHealth() + baseArmor) - monsterSkill.getValue());
            attribute.setBaseArmor(0L);
        } else {
            attribute.setBaseArmor(baseArmor - monsterSkill.getValue());
        }
        //被打败
        if (attribute.getBaseHealth() <= 0) {
            roleCacheUtils.deleteRole(role);
            monsterCacheUtils.deleteMonster(monster,role);
            mapCacheUtils.deleteMap(role.getId(), role.getLayerNumber());
            return null;
        }
        role.setAttribute(attribute);
        roleCacheUtils.initRole(role,7L);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("role",role);
        resultMap.put("monsterSkill",monsterSkill);
        return resultMap;
    }

    /**
     * 获取怪物的此次攻击
     * @param skill 技能组
     * @param probability 随机值
     * @return 抽中的skill
     */
    private MonsterSkill changeMonsterSkill(List<MonsterSkill> skill,int probability){
            List<MonsterSkill> tempList = new ArrayList<>();
            do {
                for (MonsterSkill monsterSkill : skill) {
                    if (0 < probability && probability <= monsterSkill.getProbability()){
                        tempList.add(monsterSkill);
                    }
                }
                probability = ProbabilityUtils.getProbability();
            }while (tempList.size() < 1);
            probability  = new Random().nextInt(tempList.size());
        return tempList.get(probability);
    }

    @Override
    public HealResultVO getHeal(PlayerRole role, BaseCard card) {
        PlayerAttribute attribute = role.getAttribute();
        if (attribute.getBaseHealth() + card.getValue() >= attribute.getMaxHealth()){
            attribute.setBaseHealth(attribute.getMaxHealth());
        }else {
            attribute.setBaseHealth(attribute.getBaseHealth() + card.getValue());
        }
        role.setAttribute(attribute);
        roleCacheUtils.initRole(role,7L);
        HealResultVO healResultVO = new HealResultVO();
        healResultVO.setMsg("治疗+" + card.getValue());
        healResultVO.setRole(role);
        healResultVO.setCard(card);
        return healResultVO;
    }

    @Override
    public PlayerRole increaseArmor(PlayerRole role, BaseCard card) {
        PlayerAttribute attribute = role.getAttribute();
        attribute.setBaseArmor(card.getValue() + attribute.getBaseArmor());
        role.setAttribute(attribute);
        roleCacheUtils.initRole(role,7L);
        return role;
    }
}
